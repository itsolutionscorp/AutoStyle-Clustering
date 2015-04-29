def is_leap_year(year):
    if isinstance(year, (int, long)):
        divisible_by_4 = year % 4 == 0
        divisible_by_100 = year % 100 == 0
        divisible_by_400 = year % 400 == 0

        # A year will be a leap year if:
        # - It is divisible by 4
        # - If it is divisible by 100, it is also divisible by 400
        return divisible_by_4 and (not divisible_by_100 or divisible_by_400)

    else:
        return False
