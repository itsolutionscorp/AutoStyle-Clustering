from datetime import timedelta
#from datetime import datetime

def add_gigasecond(dob):
    '''
    program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.

    A gigasecond is one billion (10**9) seconds.

    :param dob:
    :return:
    '''

    #calc days,hours,mins and second for gigasecond

    days = (10**9) // (60*60*24)
    hrs  = ((10**9) % (60*60*24)) // (60*60)
    mins = ((10**9) % (60*60)) // 60
    secs = (10**9) % 60

    time_difference = timedelta( days=days, hours=hrs,
                          minutes=mins, seconds=secs)
#    print(time_difference)
#    print(dob+time_difference)
    return dob+time_difference

#add_gigasecond(datetime(1978, 6, 12))
