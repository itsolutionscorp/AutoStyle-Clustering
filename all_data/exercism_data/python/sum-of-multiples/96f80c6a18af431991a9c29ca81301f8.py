class SumOfMultiples:
    """Returns the sum of all multiples of inputs up to a limit, excluding itself"""

    # default to 3, 5
    multiples = [3,5]

    def __init__(self, *args):
        """Creates a SumOfMults object"""
        if args:
            self.multiples = args

    def to(self, limit=10):
        """Returns the sum of the multiples of mults up to a given limit"""
        sum_multiples = set()
        for mult in self.multiples:
            sum_multiples = sum_multiples.union(set(filter(lambda x: not x % mult, range(1, limit))))
        return sum(sum_multiples)
    
