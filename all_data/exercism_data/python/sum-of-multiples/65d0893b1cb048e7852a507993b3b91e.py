from itertools import combinations, starmap, cycle
from operator import add, sub
from fractions import gcd

class SumOfMultiples:

    def __init__(self, *factors):
        factors = factors or (3, 5)
        self._factors = self._filter_redundant_factors(factors)

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

    def to(self, to):
        """Computes the sum of multiples for all configured factors up to the
        value of the 'to' parameter. Duplicate multiples are only counted
        once in the sum.
        """
        return sum(
            plus_or_minus * sum_of_multiples
            for plus_or_minus, sum_of_multiples
            in self._steps(to)
        )

    def _steps(self, to):
        plus_or_minus = +1

        sum_of_multiples = self._sum_of_multiples(self._factors, to)
        yield (plus_or_minus, sum_of_multiples)

        for combi_length in range(2, len(self._factors) + 1):
            plus_or_minus = -plus_or_minus
            factors = self._lcm_for_factor_combinations(combi_length, to)
            sum_of_multiples = self._sum_of_multiples(factors, to)
            yield (plus_or_minus, sum_of_multiples)

    def _lcm_for_factor_combinations(self, combi_len, to):
        return [
            factor for factor in [
                self._lcm(*factors)
                for factors in combinations(self._factors, combi_len)
            ] if factor < to
        ] 

    def _lcm(self, *factors):
        if len(factors) == 1:
            return factors[0]
        elif len(factors) == 2:
            return (factors[0] * factors[1]) // gcd(*factors)
        else:
            return reduce(self._lcm, factors)

    def _sum_of_multiples(self, factors, to):
        total = 0
        for factor in factors:
            c = (to - 1) // factor
            total += factor * (c * c + c) // 2
        return total
