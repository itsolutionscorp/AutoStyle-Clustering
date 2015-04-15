def find_multiples(mults, limit):
    """Returns multiples of each mults up to to"""
    multiples = []
    for i in range(3, limit):
        for mult in mults:
            if i % mult == 0:
                # print ('Found multiple', i)
                multiples += [i]
    return set(multiples)

class SumOfMultiples:
    """Returns the sum of all multiples of inputs up to a limit, excluding itself"""

    def __init__(self, *mults):
        """Creates a SumOfMults object"""
        if not mults:
            mults = [3,5]
        self._mults = mults

    def to(self, limit=10):
        """Returns the sum of the multiples of mults up to a given limit"""
        return sum(find_multiples(self._mults, limit))

if __name__ == "__main__":
    a = SumOfMultiples().to(10)
        
    
