__user__="cdances"

from datetime import date,timedelta

gigasecond=10**9

def add_gigasecond( startdate ):
    return startdate + timedelta(seconds=gigasecond)

if __name__=="__main__":
    print add_gigasecond( date(2011, 4, 25) )
    
