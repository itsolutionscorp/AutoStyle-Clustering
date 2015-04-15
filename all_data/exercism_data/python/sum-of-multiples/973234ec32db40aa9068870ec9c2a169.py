class SumOfMultiples:

    def __init__(self, *factors):
        self._factors = factors or [ 3, 5 ]

    def to(self, to_number):

        all_multiples = [ ] 
        for factor in self._factors:
            all_multiples += [ m for m in range(factor, to_number, factor) ]

        unique_multiples = set(all_multiples)
        sum_of_multiples = sum(unique_multiples)

        return sum_of_multiples
