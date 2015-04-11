def is_leap_year(year):
    def year_divisible_by(n):
        return not bool(year % n)
    return (year_divisible_by(4) and not year_divisible_by(100))\
        or year_divisible_by(400)
