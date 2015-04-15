def sieve(limit):
    return list(PrimeIter(limit))


class PrimeIter:
    """
    An Iterator, that returns prime numbers up to a given limit.
    """

    def __init__(self, limit):
        """
        :param int limit:
        """
        if type(limit) is not int:
            raise TypeError('Limit must be of type integer.')

        self.limit = limit
        self.index = 2
        self.primes = []

    def __iter__(self):
        return self

    def __next__(self):
        """
        Get next prime number.

        :return: int
        """
        self.index = self.__get_next_unmarked(self.index)

        if self.index > self.limit:
            raise StopIteration

        self.primes.append(self.index)
        return self.index

    def __get_next_unmarked(self, current):
        """
        Get the next unmarked number.

        :param int current:
        :return: int
        """
        while self.__is_multiple(current, self.primes):
            current += 1
        return current

    def __is_multiple(self, current, primes):
        """
        Check, whether current index is a multiple of one of the already discovered primes.

        :param int current:
        :return: bool
        """
        return any(current % prime == 0 for prime in primes)
