def is_leap_year(year):

    """Runs algorithm on argument year to determine whether it is a leap year.
       Returns True or False.
    """
    
    if year % 4 == 0:
        if year % 400 == 0:
            leapYear = True
        elif year % 100 == 0:
            leapYear = False
        else:
            leapYear = True
    else:
        leapYear = False

    return leapYear


# ---- main

year = 0
yesno = ''
response = ('y', 'Y', 'n', 'N')
terminate = False

while not terminate:
    try:
        while year < 1:
            year = int(input('Enter year: '))
            if year < 1:
                print('INVALID REPLY - Year must be a positive value.')
        leapYear = is_leap_year(year)
        if leapYear:
            print(year, 'is a leap year.')
            year = 0
        else:
            print(year, 'is not a leap year.')
            year = 0
        yesno = ''
        while yesno not in response:
            yesno = input('Do you wish to check another year (y/n)? ')
            if yesno == 'n' or yesno == 'N':
                terminate = True
            elif yesno == 'y' or yesno == 'Y':
                terminate = False
            else:
                print('INVALID REPLY - Do you wish to check another year (y/n)? ')
    except TypeError:
        print('INVALID REPLY - Please enter a valid year.')
