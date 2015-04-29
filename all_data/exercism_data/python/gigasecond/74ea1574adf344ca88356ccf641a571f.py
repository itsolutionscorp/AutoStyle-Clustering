__user__="cdances"

from datetime import date,timedelta

def add_gigasecond( startdate ):
    return startdate + timedelta(seconds=10**9)

if __name__=="__main__":
    print add_gigasecond( date(2011, 4, 25) )
    
