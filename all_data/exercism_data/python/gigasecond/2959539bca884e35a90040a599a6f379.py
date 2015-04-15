'''
I've already submitted the "clever" solution to this problem (it's still
up as a past revision) but, while it's good to know those libraries exist,
it makes this a pretty boring problem. So I redid it from scratch and it
was much more fun than just writing a program that adds two numbers.

I essentially use the Euclidean Algorithm to build up the years until we
can't anymore without going over a billion. Then do the same for months and days.
While doing this, one of the given dates ends the year-cycle on a leap year and
while I would have accounted for the leap day when I added the next year, that
didn't happen this time. So I had to add a line into the code that took care of
that situation. Took me a little to figure out what was happening, but printing
and looking through the data to see the patterns and what it was
doing was pretty fun.

I like this solution better.
'''

from datetime import date

def is_leap_year(year):
    
    return (year%4 == 0 and (year%100!=0 or year%400 == 0))

def add_by_year(sec, year):
    if is_leap_year(year):
        return sec+86400*366 #Add the seconds in a leap year
    return sec+86400*365 #Add the seconds in a standard year

def add_by_month(sec,month, year):
    A=86400 #seconds in a day

    #Number of days in each of the various months of the year.
    standard={1:31 , 2:28, 3:31, 4:30, 5:31, 6:30, 7:31, 8:31, 9:30, 10:31, 11:30, 12:31}
    leap = {1:31 , 2:29, 3:31, 4:30, 5:31, 6:30, 7:31, 8:31, 9:30, 10:31, 11:30, 12:31}

    if is_leap_year(year):
        return sec+A*leap[month]
    return sec+A*standard[month]

def add_by_day(sec):
    return sec+86400


#The idea is to take advantage of the Euclidean Algorithm for years, months and days.
def add_gigasecond(b_day):
    sec=0
    year=b_day.year
    month=b_day.month
    day=b_day.day

    A=86400 #seconds in a day

    #Number of days in each of the various months of the year.
    standard={1:31 , 2:28, 3:31, 4:30, 5:31, 6:30, 7:31, 8:31, 9:30, 10:31, 11:30, 12:31}
    leap = {1:31 , 2:29, 3:31, 4:30, 5:31, 6:30, 7:31, 8:31, 9:30, 10:31, 11:30, 12:31}


   #First we add up years until we can't without going over
    while add_by_year(sec, year)<=1000000000:
        sec=add_by_year(sec,year)
        year=year+1

    #If we end on a leap year, we have to account for it one last time is we went over
    if is_leap_year(year) and month>2:
            day=day-1

    #Do the same for months, we much be careful to carry the remainders
    while add_by_month(sec,month, year)<=1000000000:
        sec=add_by_month(sec,month,year)
        if month==12:
            month=1
            year=year+1
        else:
            month=month+1
    #Then days, carrying remainders for months and years. I probably could have just written a function that adds months and days.
    while add_by_day(sec)<=1000000000:
        sec=add_by_day(sec)

        if is_leap_year(year):
            if day==leap[month]:
                day=1
                if month==12:
                    month=1
                    year=year+1
                else:
                    month=month+1
            else:
                day=day+1
        else:
            if day==standard[month]:
                day=1
                if month==12:
                    month=1
                    year=year+1
                else:
                    month=month+1
            else:
                day=day+1

   
    return date(year,month,day)

        
            
        
            
        
