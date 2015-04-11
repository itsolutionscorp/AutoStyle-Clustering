import datetime
import sys

weekdays =  ("Monday",
             "Tuesday",
             "Wednesday",
             "Thursday",
             "Friday",
             "Saturday",
             "Sunday"
            )

def nextOneOfDay(date, desiredDow):
    datetimeDow = datetime.date.weekday(date) 
    days_hence = desiredDow - datetimeDow
    if days_hence < 0:
        days_hence += 7
    return date + datetime.timedelta(days_hence)

def get_nth(n, date, desiredDow):
    nextCandidate = nextOneOfDay(date, desiredDow);
    for n in range (0, n-1):
        nextCandidate = nextOneOfDay(nextCandidate + datetime.timedelta(1), desiredDow)
    if nextCandidate.month == date.month:
        return nextCandidate
    else:
        return None

def get_teenth(date, desiredDow):
    nextCandidate = nextOneOfDay(date, desiredDow)
    while not(set([nextCandidate.day]) < set([13,14,15,16,17,18,19])):
        nextCandidate = nextOneOfDay(nextCandidate + datetime.timedelta(1), desiredDow)
    return nextCandidate

def get_last(date, desiredDow):
    nextCandidate = nextOneOfDay(date, desiredDow)
    while nextOneOfDay(nextCandidate + datetime.timedelta(1),desiredDow).month == date.month:
        nextCandidate = nextOneOfDay(nextCandidate + datetime.timedelta(1),desiredDow)
    return nextCandidate

def meetup_day(year, month, weekday, ordinal):
    
    dow = weekdays.index(weekday)
    firstOfMonth = datetime.date(year, month, 1)
    choice = {"1st": get_nth(1, firstOfMonth, dow), 
              "2nd": get_nth(2, firstOfMonth, dow),
              "3rd": get_nth(3, firstOfMonth, dow),
              "4th": get_nth(4, firstOfMonth, dow),
              "5th": get_nth(5, firstOfMonth, dow),
              "teenth": get_teenth(firstOfMonth, dow),
              "last": get_last(firstOfMonth, dow)
             } 
    return choice[ordinal]

if __name__ == "__main__":
    desiredDate = meetup_day(int(sys.argv[1]), int(sys.argv[2]), sys.argv[3], sys.argv[4])
    print desiredDate
