def is_leap_year(year):

    # First check if this is an actual number
    # Also check that the number is positive
    if isinstance(year, (int, long)) and year >= 0:

        # Evaluate these conditions in advance for the sake of clarity
        divisible_by_4 = year % 4 == 0
        divisible_by_100 = year % 100 == 0
        divisible_by_400 = year % 400 == 0

        # A year will be a leap year if:
        # - It is divisible by 4
        # - If it is divisible by 100, it is also divisible by 400
        return divisible_by_4 and (not divisible_by_100 or divisible_by_400)

    else:
        # Return False because this isn't even a valid number
        return False
