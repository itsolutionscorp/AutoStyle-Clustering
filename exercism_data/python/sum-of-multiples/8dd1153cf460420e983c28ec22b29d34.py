from itertools import combinations, starmap, cycle
from operator import add, sub
from fractions import gcd

class SumOfMultiples:

    def __init__(self, *factors):
        self.factors = factors or (3, 5)

    def to(self, to_number):
        """Computes the sum of multiples for all configured factors up to the
        value of the 'to_number' parameter. Duplicate multiples are only counted
        once in the sum.
        """
        total = 0
        factors = self.factors
        for add_or_sub in cycle((add, sub)):
            factors = list(self._filter_factors(factors, to_number))
            if not factors:
                break
            sum_of_multiples = self._sum_of_multiples(factors, to_number)
            total = add_or_sub(total, sum_of_multiples)
            factors = self._least_common_multiples_for_factors(factors)
        return total

    def _filter_factors(self, factors, to_number):
        """Deletes duplicate factors, factors that are higher than the
        to_number and factors that can be divided by a lower factor. These all
        would add nothing to the final outcome.
        """
        sorted_factors = list(sorted(factors))
        seen_factors = []
        for idx, factor in enumerate(sorted_factors):
            if factor < to_number:
                if not any(f for f in seen_factors if not factor % f):
                    seen_factors.append(factor)
                    yield factor

    def _sum_of_multiples(self, factors, to_number):
        def sum_of_multiples_for_factor(factor):
            c = (to_number-1) // factor
            return factor * (c * c + c) // 2
        return sum(map(sum_of_multiples_for_factor, factors))

    def _least_common_multiples_for_factors(self, factors):
        return [
            factor1 * factor2 // gcd(factor1, factor2)
            for factor1, factor2 in combinations(factors, 2)
        ]
