from datetime import date, timedelta

def add_gigasecond(dT):
    return dT + timedelta(seconds = 10**9)

if __name__ == '__main__':
    print(add_gigasecond(date(2011, 4, 25)))
