# Leap.py

year = raw_input("Please enter a year: ")



def checkLeapYear( year ):
    
    year = int(year)
    
    if year % 4 != 0:
        print "no"
        return False
    if year % 100 == 0 and year % 400 != 0:
        print "no"
        return False
    print "yes"
    return True
    
    
checkLeapYear ( year );
    
