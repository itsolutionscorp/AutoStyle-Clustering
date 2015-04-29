__author__ = 'agupt15'


def prime_factors(n):

    def find_factors(num, div, factors):
        if num == 1:
            return factors
        while num > 1:
            remainder = num % div
            if remainder == 0:
                factors.append(div)
                return find_factors(num / div, div, factors)
            else:
                div += 1

    return find_factors(n, 2, [])
