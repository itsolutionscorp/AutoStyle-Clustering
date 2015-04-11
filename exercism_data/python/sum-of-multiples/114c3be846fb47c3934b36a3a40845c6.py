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
        factors = self._filter_redundant_factors(self.factors)
        for add_or_sub in cycle((add, sub)):
            factors = self._filter_dup_and_high_factors(factors, to_number)
            if not factors:
                break
            sum_of_multiples = self._sum_of_multiples(factors, to_number)
            total = add_or_sub(total, sum_of_multiples)
            factors = self._least_common_multiples_for_factors(factors)
        return total

    def _filter_redundant_factors(self, factors):
        """Deletes duplicate factors and factors that can be divided by a
        lower factor. These factors will always produce duplicate multiples
        that all must be subtracted from the total. So better not use them
        in the first place.
        """
        return [
            factor for factor in set(factors)
            if not any(
                other_factor for other_factor in factors
                if other_factor < factor and not factor % other_factor
            )
        ]

    def _filter_dup_and_high_factors(self, factors, to_number):
        """Deletes duplicate factors (as required by the algorithm) and factors
        that are higher than the to_number (hese all would add nothing to the
        final outcome).
        """
        return [
            factor for factor in set(factors)
            if factor < to_number
        ]

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
