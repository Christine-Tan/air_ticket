#!/usr/bin/python
# -*- coding: utf-8 -*-
from sklearn.ensemble import RandomForestRegressor
import pickle
import csv
from http.server import BaseHTTPRequestHandler, HTTPServer
import time


def get_city_code():
    city_to_code = {
        'BJS': 1,
        'SZX': 2,
        'CTU': 4,
        'CSX': 8,
        'NKG': 16,
        'XMN': 32,
        'KMG': 64,
        'DLC': 128,
        'SHA': 256,
        'CAN': 512,
    }
    return city_to_code


class Persistent:
    """
    这个类用于持久化预测模型
    """
    price_model_file = 'price.pk'
    days_model_file = 'days.pk'

    def save_day(self, model):
        with open(self.days_model_file, 'wb') as file:
            pickle.dump(model, file)

    def load_day(self):
        with open(self.days_model_file, 'rb') as file:
            return pickle.load(file)

    def save_price(self, model):
        with open(self.price_model_file, 'wb') as file:
            pickle.dump(model, file)

    def load_price(self):
        with open(self.price_model_file, 'rb') as file:
            return pickle.load(file)


def load_train_data():
    """
    这个方法会加载训练数据
    :return: (训练集，训练预计提前天数标签，训练价格标签)
    """
    train_data_file_name = 'train.csv'
    with open(train_data_file_name) as read_train_data_file:
        reader = csv.reader(read_train_data_file)
        x = []
        y = []
        p = []
        for i in reader:
            x.append(i[:-2])
            y.append(i[-1])
            p.append(i[-2])
        return x, y, p


def train():
    """
    训练
    :return:None 
    """
    x, days, prices = load_train_data()
    # 加载数据
    persistent = Persistent()
    # 用于持久化

    forest = RandomForestRegressor(verbose=True)
    forest.fit(x, prices)
    # 训练预测价格模型
    persistent.save_price(forest)
    # 存储价格预测模型

    forest = RandomForestRegressor()
    forest.fit(x, days)
    # 训练预测提前购买天数模型
    persistent.save_day(forest)
    # 存储预测提前购买天数模型


def do_service():
    """
    加载模型
    进行预测
    :return: 
    """
    persistent = Persistent()
    days_predict = persistent.load_day()
    # 加载预测提前购买天数模型
    prices_predict = persistent.load_price()
    # x, days, prices = load_train_data()

    class MyWebHandler(BaseHTTPRequestHandler):

        city_code = get_city_code()


        def do_GET(self):
            self.send_response(200)
            self.send_header("Content-type", "text/json")
            self.end_headers()
            parameters = (self.path.split('/'))

            def get_features(para):
                try:
                    date = time.strptime(str(para[1]) + ' ' + para[2], '%Y-%m-%d %H:%M:%S')
                    return [date.tm_year, date.tm_mon, date.tm_mday,
                            date.tm_hour, date.tm_min, date.tm_wday,
                            self.city_code[para[3]], self.city_code[para[4]]
                            ]
                except Exception as e:
                    print(e)
                    return None
            features = get_features(parameters)
            if features is None:
                self.wfile.write(str({'result': False}).encode('utf-8'))
            else:
                prices = prices_predict.predict(features)
                days = days_predict.predict(features)
                self.wfile.write(str({'result': True, 'price': prices[0], 'days': days[0]}).encode('utf-8'))
            self.wfile.close()

    try:
        server = HTTPServer(('localhost', 8088), MyWebHandler)
        print('Started http server')
        server.serve_forever()
    except KeyboardInterrupt:
        print('^C received, shutting down server')
        server.socket.close()

if __name__ == '__main__':
    # train()
    do_service()
    # persistent = Persistent()
    # forest = RandomForestRegressor(verbose=True)
    # persistent.save_day(forest)


