def is_leap_year(yr):
    '''
    Return True if input year(number) is a Leap Year,
    returns False otherwise.
    '''
    #Return True if year is divisible by 4...
    #AND is either not divisible by 100
    #OR is divisible by 400    
    return (yr % 4 == 0
            and yr % 100 != 0
            or yr % 400 == 0)

    
