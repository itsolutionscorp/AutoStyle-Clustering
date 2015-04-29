def is_leap_year(year):
    '''
    Rule says:
        on every year that is evenly divisible by 4
            except every year that is evenly divisible by 100
                unless the year is also evenly divisible by 400
    
    Given:
        X = year divisible by 4
        Y = year divisible by 100
        Z = year divisible by 400
    Then by truth table analyzis:
        IsLeap = (X and !Y and !Z) or (X and !Y and Z) or (X and Y and Z)
    Re-expressed using minimal form analysis:
        IsLeap = X and (!Y or Z)
        
    Modulus operator  (%) determines if the first operand is evenly divisible by the second
    '''
    if not year % 4  and (year % 100 or not year % 400):
        return True
    else:
        return False
