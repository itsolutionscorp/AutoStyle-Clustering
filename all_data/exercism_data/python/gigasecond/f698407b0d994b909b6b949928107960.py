from datetime import date, timedelta

def add_gigasecond(bday):
    bday_Gs = bday + timedelta(seconds = 10**9)
    return bday_Gs

if __name__ == '__main__':
    print('1 Gs bday:', add_gigasecond(date(1993, 4, 16)))
