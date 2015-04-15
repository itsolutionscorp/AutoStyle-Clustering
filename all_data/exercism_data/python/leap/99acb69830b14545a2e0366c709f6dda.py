def is_divisible(N, M):
    """Check if N is evenlyis_divisible by M."""
    return N % M == 0

def is_leap_year(year):
    """Check if a year is a leap year."""
    if is_divisible(year, 400):
        return True
    elif is_divisible(year, 100):
        return False
    elif is_divisible(year, 4):
        return True
    else:
        return False
