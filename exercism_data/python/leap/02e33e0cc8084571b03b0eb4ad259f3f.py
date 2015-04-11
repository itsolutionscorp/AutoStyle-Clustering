def divisible_by(divisor):
    def divisible(num):
        return num % divisor == 0

    return divisible


def is_leap_year(year):
    is_divisible_by_4 = divisible_by(4)
    is_divisible_by_100 = divisible_by(100)
    is_divisible_by_400 = divisible_by(400)
    
    return (is_divisible_by_4(year) and
            not is_divisible_by_100(year) or
            is_divisible_by_400(year))
