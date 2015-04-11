def sum_of_multiples(limit, factors=[3, 5]):
    """
    Returns the sum of all multiples of the given factors up to the given limit number.

    :param int limit:
    :param [int] factors:
    :return: int
    """
    return sum(__get_multiples(limit, factors))


def __get_multiples(limit, factors):
    """
    Returns all multiples of the given factors up to the given limit number.

    :param int limit:
    :param [int] factors:
    :return: collections.Iterable[int]
    """
    return (num for num in range(limit) if any(num % factor == 0 for factor in factors if factor > 0))
