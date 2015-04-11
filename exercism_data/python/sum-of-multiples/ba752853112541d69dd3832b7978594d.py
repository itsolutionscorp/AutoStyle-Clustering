from itertools import combinations
from fractions import gcd


class SumOfMultiples:

    def __init__(self, *factors):
        factors = factors or (3, 5)
        self._validate_factors(factors)
        self._factors = self._filter_superfluous_factors(factors)

    @staticmethod
    def _validate_factors(factors):
        for factor in factors:
            if factor < 0:
                raise ValueError(
                    "Invalid factor: %s (factors must be >= 0)" % factor)

    @staticmethod
    def _filter_superfluous_factors(factors):
        """Delete duplicate factors and factors that can be divided by a
        lower factor.  These factors will always produce duplicate multiples
        that all must be subtracted from the total, so better not use them
        in the first place.  Also delete zero factors.
        """
        return [
            factor for factor in set(factors)
            if factor != 0 and not any(
                other_factor for other_factor in factors
                if other_factor and
                   other_factor < factor and
                   not factor % other_factor
            )
        ]

    def to(self, until):
        """Compute the sum of multiples for all configured factors up to the
        value of the 'until' parameter. Duplicate multiples are only counted
        once in the sum.
        """
        return sum(
            plus_or_minus * sum_of_multiples
            for plus_or_minus, sum_of_multiples
            in self._steps(until)
        )

    def _steps(self, until):
        plus_or_minus = +1

        sum_of_multiples = self._sum_of_multiples(self._factors, until)
        yield (plus_or_minus, sum_of_multiples)

        for combi_length in range(2, len(self._factors) + 1):
            plus_or_minus = -plus_or_minus
            factors = self._lcm_for_factor_combinations(combi_length, until)
            sum_of_multiples = self._sum_of_multiples(factors, until)
            yield (plus_or_minus, sum_of_multiples)

    def _lcm_for_factor_combinations(self, combi_len, until):
        return [
            factor for factor in [
                self._lcm(*factors)
                for factors in combinations(self._factors, combi_len)
            ] if factor < until
        ]

    def _lcm(self, *factors):
        if len(factors) == 1:
            return factors[0]
        elif len(factors) == 2:
            return (factors[0] * factors[1]) // gcd(*factors)
        else:
            lcm = factors[0]
            for factor in factors[1::]:
                lcm = self._lcm(lcm, factor)
            return lcm

    @staticmethod
    def _sum_of_multiples(factors, until):
        total = 0
        for factor in factors:
            c = (until - 1) // factor
            total += factor * (c * c + c) // 2
        return total
