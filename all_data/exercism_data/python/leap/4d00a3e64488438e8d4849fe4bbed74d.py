def is_leap_year(a_year):
    
    # evaluate modulo function (a_year % 4):
    # if a_year is not evenly divisible by 4 -> a_year % 4 != 0
    # if [any number other than zero] -> True
    # since condition is True, execute following statement
    if a_year % 4:
        return False
    
    # evaluate two modulo functions at the same time:
    # if a_year is evenly divisible by 100 -> a_year % 100 = 0
    # not 0 -> True
    # if a_year is not evenly divisible by 400 -> a_year % 400 != 0
    # if [any number other than zero] -> True
    # since both conditions are True, execute the following statement
    elif not a_year % 100 and a_year % 400:
        return False

    # all non-leap-years have been filtered out,
    # so only leap years remain
    else:
        return True

if __name__ == '__main__':
    year = int(input('input year: '))
    print(is_leap_year(year))
