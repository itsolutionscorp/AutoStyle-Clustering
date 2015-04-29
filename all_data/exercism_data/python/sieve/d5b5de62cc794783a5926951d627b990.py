def sieve(until):
    """
    Find all the primes from 2 up to a given number.
    """
    return SieveOfEratosthenes().sieve(until)

class SieveOfEratosthenes:

    UNKNOWN   = None
    PRIME     = True
    COMPOSITE = False

    def sieve(self, until):
        self._set_until(until)
        self._init_numbers()
        self._flag_numbers()
        return self._get_primes()

    def _set_until(self, until):
        if until < 2:
            raise ValueError("Parameter 'until' must be greater than 1")
        self._until = until

    def _init_numbers(self):
        numbers = xrange(2, self._until + 1)
        self._numbers = dict([ (c, self.UNKNOWN) for c in numbers ])

    def _flag_numbers(self):
        for number in self._numbers:
            if self._numbers[number] == self.COMPOSITE:
                continue
            self._numbers[number] = self.PRIME
            self._flag_multiples_as_composite(number)

    def _flag_multiples_as_composite(self, number):
        for composite in xrange(number * 2, self._until + 1, number):
            self._numbers[composite] = self.COMPOSITE

    def _get_primes(self):
        return [
            number for number in self._numbers 
            if self._numbers[number] == self.PRIME
        ]
