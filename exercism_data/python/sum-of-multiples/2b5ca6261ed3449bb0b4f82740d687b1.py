"""The sum of multiples."""


class SumOfMultiples(object):
    """The sum of multiples."""

    def __init__(self, *divisors):
        self.divisors = divisors if divisors else (3, 5)

    def to(self, upto):
        """Return the sum of all the multiples up to a given number."""
        multiples = (
            number
            for number in xrange(1, upto)
            if any(number % divisor == 0 for divisor in self.divisors)
        )
        return sum(multiples)
