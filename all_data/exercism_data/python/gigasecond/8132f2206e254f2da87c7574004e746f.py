__author__="cdances"

from datetime import date,timedelta

gigasecond=timedelta(seconds=10**9)

def add_gigasecond( startdate ):
    return startdate + gigasecond 

if __name__=="__main__":
    print add_gigasecond( date(2011, 4, 25) )
    
