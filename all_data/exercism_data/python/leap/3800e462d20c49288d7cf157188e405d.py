class Number():
    
    def __init__(self, number):
        self.number = number

    def is_divisible_by(self, divisor):
        '''Number.is_divisible_by( divisor )

        Tests whether the number is divisble by the divisor

        Arguments:
            divisor - The number that the year is divided by
        Returns:
            True if divisible by the divisor argument
            False if not divisible by the divisor argument
        '''

        if self.number % divisor == 0: return True
        return False

def is_leap_year( year ):
    '''leap( year )

    Tests whether the given year is a leap year
    '''

    test_year = Number(year)
    
    # The order of the testing is important. Because each successive
    # test will be true if the last was true.
    if test_year.is_divisible_by(400): return True
    if test_year.is_divisible_by(100): return False
    if test_year.is_divisible_by(4): return True
    return False
