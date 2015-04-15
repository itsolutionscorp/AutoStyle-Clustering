class Year:
    """
    Class that contain functions to manage years
    """

    @staticmethod
    def is_leap_year(year):
        """
        Take a year and report if it is a leap year
        """
        if year % 400 == 0:
            return True
        if year % 100 == 0:
            return False
        if year % 4 == 0:
            return True
        return False


def is_leap_year(year):
    return Year.is_leap_year(year)
