def is_leap_year(year):
    def divisible_by(number):
        return year % number == 0

    return divisible_by(400) or \
           (divisible_by(4) and
            not(divisible_by(100)))
