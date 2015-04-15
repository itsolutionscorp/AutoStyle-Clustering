from datetime import date,timedelta
import sys

def add_gigasecond(input_date):
    gigasecond_time = timedelta(seconds=(10**9))
    return input_date + gigasecond_time

def input_add_gigasecond(year,month,day):
    return add_gigasecond(date(int(year),int(month),int(day)))
    
if __name__ == '__main__':
    print input_add_gigasecond(sys.argv[1],sys.argv[2],sys.argv[3])
