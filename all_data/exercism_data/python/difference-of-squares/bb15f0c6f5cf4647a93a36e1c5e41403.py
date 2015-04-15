

def difference(upper_limit: int) -> int:
    return (3 * upper_limit**4 + 2 * upper_limit**3 - 3 * upper_limit**2 - 2 * upper_limit) / 12


def square_of_sum(upper_limit: int) -> int:
    return ((1 + upper_limit) * upper_limit / 2)**2


def sum_of_squares(upper_limit: int) -> int:
    return upper_limit * (upper_limit+1) * (2*upper_limit+1) / 6
