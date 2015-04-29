#
#  Determines if a year is a leap year
#
import sys
def is_leap_year(year):

    analysis = False
    if 0 == year % 4:
        analysis = True
        if 0 == year % 100:
            analysis = False
            if 0 == year % 400:
                analysis = True
        
    return analysis


if __name__ == '__main__':  
    print is_leap_year(int(sys.argv[1]))
