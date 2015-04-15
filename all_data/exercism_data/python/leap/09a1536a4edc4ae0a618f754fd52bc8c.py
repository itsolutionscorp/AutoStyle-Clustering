def is_leap_year(year):
    def divisible(a, b):
        return a % b == 0

    return (divisible(year, 4) and (not divisible(year, 100)
                                    or divisible(year, 400)))
