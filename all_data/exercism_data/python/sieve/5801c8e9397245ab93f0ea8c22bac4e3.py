"""The Sieve of Eratosthenes."""

import array


def divisible(x, y):
    """Return true if the 1st argument is divisible by the 2nd argument."""
    return x % y == 0


class BitArray(object):
    """A memory-efficient bit array."""

    # We assume that the size of ``unsigned int`` is 32 bits.
    _UNSIGNED_INT = 32

    def __init__(self, nbits, fill=0):
        """Create a bit array of a given size filled with zeros or ones.

        :param nbits: the size of an array in bits.
        :param fill: 0 or 1.
        """
        # assuming that the size of ``unsigned int`` is 32 bits.
        if divisible(nbits, BitArray._UNSIGNED_INT):
            nints = nbits // BitArray._UNSIGNED_INT
        else:
            nints = (nbits // BitArray._UNSIGNED_INT) + 1
        fill = (2 ** BitArray._UNSIGNED_INT - 1) if fill else 0
        self.bits = array.array('I')
        for _ in xrange(nints):
            self.bits.append(fill)

    def __getitem__(self, index):
        """Get the bit at ``index``."""
        nth_int, nth_bit = divmod(index, BitArray._UNSIGNED_INT)
        return self.bits[nth_int] & (1 << nth_bit)

    def __setitem__(self, index, fill):
        """Set the bit at ``index`` to ``fill``.

        :param fill: 0 or 1.
        """
        nth_int, nth_bit = divmod(index, BitArray._UNSIGNED_INT)
        if fill:
            self.bits[nth_int] |= (1 << nth_bit)
        else:
            self.bits[nth_int] &= ~(1 << nth_bit)


def prime_numbers(upto):
    """ Generate prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    """
    sieve = BitArray(upto + 1, 1)
    for number in xrange(2, upto + 1):
        if not sieve[number]:
            continue
        yield number
        for multiple in xrange(number ** 2, upto + 1, number):
            sieve[multiple] = 0
    return


def sieve(upto):
    """ Return a list of prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    :returns: a list of prime numbers up to a given number.
    :rtype: list of int.
    """
    return list(prime_numbers(upto))
