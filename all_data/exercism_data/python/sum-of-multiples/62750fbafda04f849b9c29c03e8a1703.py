__author__ = 'gdunn'


def sum_of_multiples(num, factors=None):
    """



    :type factors: list
    :type num: int
    :rtype : int
    :param num: The int to count up to
    :param factors: The factors to check divisibilty against
    :return: The sum of the integers < num that are divisible by factors
    """
    if not factors: factors = [3, 5]
    the_sum = 0
    for i in range(1, num):
        for factor in factors:
            if i % factor == 0:
                the_sum += i

    return the_sum
