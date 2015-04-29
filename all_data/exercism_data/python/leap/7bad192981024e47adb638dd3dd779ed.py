def is_leap_year(y):
    def is_divisible_by(n):
        return y % n == 0;

    if not is_divisible_by(4):
        return False;

    if is_divisible_by(400):
        return True;

    if is_divisible_by(100):
        return False;

    return True;
