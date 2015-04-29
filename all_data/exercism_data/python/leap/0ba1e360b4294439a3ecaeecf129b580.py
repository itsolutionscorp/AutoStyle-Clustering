def is_leap_year(yr):
    '''
    Return True if input year(number) is a Leap Year,
    returns False otherwise.
    '''
    #Return True if year is divisible by 4...
    return (0 == yr / 4 % 1
            
        #AND is either not divisible by 100
        and not 0 == yr / 100 % 1
            
        #OR is divisible by 400
        or 0 == yr / 400 % 1)
