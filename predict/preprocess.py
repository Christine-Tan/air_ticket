import csv
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

if __name__ == '__main__':
    datafile = 'data.csv'
    writefile = 'train.csv'
    city_code = get_city_code()
    if __name__ == '__main__':
        with open(datafile, newline='\n', encoding='utf-8') as read_csv_file:
            with open(writefile, 'w', newline='') as write_csv_file:
                csv_reader = csv.reader(read_csv_file)
                csv_writer = csv.writer(write_csv_file)
                for i in csv_reader:
                    print(i)
                    date = time.strptime(str(i[0]).replace('\ufeff', '') + ' ' + i[1], '%Y-%m-%d %H:%M:%S')
                    processed_data = [date.tm_year, date.tm_mon, date.tm_mday,
                                      date.tm_hour, date.tm_min, date.tm_wday,
                                      city_code[i[2]], city_code[i[3]], i[5], i[6]]
                    csv_writer.writerow(processed_data)


