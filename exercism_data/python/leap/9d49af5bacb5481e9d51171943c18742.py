#Program that takes a year and determines if that year is a leap year.
def leap_year(year):
    year = int(year)
    if year % 4 != 0:
        return False
    elif year % 100 == 0 and year % 400 != 0:
        return False
    elif year % 4 == 0:
        return True
    elif year % 400 == 0:
        return True
    
for attempt in range(0,5):
    year = input('Please enter a year: ')
    print(leap_year(year))
