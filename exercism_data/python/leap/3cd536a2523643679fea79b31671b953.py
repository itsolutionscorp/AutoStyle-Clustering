def is_leap_year(year):

    # First check if this is an actual number
    # Also check that the number is positive
    if isinstance(year, (int, long)) and year >= 0:

        # Evaluate these conditions in advance for the sake of clarity
        divisible_by_4 = year % 4 == 0
        divisible_by_100 = year % 100 == 0
        divisible_by_400 = year % 400 == 0

        # Start by checking if the number is divisible by 4
        if divisible_by_4:

            # Check if the number is divisible by 100
            if divisible_by_100:

                # This year is already divisible by 4 and 100.
                # It will be a leap year iff it is also divisible by 400.
                return divisible_by_400

            else:

                # This is a leap year because the number is divisible by 4, but not by 100
                # In this case we don't need to check if it is divisible by 400
                return True
        else:
            # This is NOT a leap year because it is not divisible by 4
            return False
    else:
        # Return False because this isn't even a valid number
        return False
