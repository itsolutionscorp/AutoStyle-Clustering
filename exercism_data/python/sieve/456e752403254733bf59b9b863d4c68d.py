"""The Sieve of Eratosthenes."""

import array


def prime_numbers(upto):
    """ Generate prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    """
    is_prime = BitArray(upto + 1, fill=1)
    for number in xrange(2, upto + 1):
        if not is_prime[number]:
            continue
        yield number
        for multiple in xrange(number ** 2, upto + 1, number):
            is_prime[multiple] = 0
    return


def sieve(upto):
    """ Return a list of prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    :returns: a list of prime numbers up to a given number.
    :rtype: list of int.
    """
    return list(prime_numbers(upto))


def divisible(x, y):
    """Return true if the 1st argument is divisible by the 2nd argument."""
    return x % y == 0


class BitArray(object):
    """A memory-efficient bit array."""

    # We use an ``array`` of unsigned integers of at least 32 bits.
    _SIZE_OF_INT_IN_BITS = 32

    def __init__(self, nbits, fill=0):
        """Create a bit array of a given size filled with zeros or ones.

        :param nbits: the size of an array in bits.
        :param fill: 0 or 1.
        """
        if divisible(nbits, BitArray._SIZE_OF_INT_IN_BITS):
            nints = nbits // BitArray._SIZE_OF_INT_IN_BITS
        else:
            nints = (nbits // BitArray._SIZE_OF_INT_IN_BITS) + 1
        fill = (2 ** BitArray._SIZE_OF_INT_IN_BITS - 1) if fill else 0
        typecode = "I" if array.array("I").itemsize >= 4 else "L"
        self.bits = array.array(typecode)
        self.bits.extend(fill for _ in xrange(nints))

    def __getitem__(self, index):
        """Get the bit at ``index``."""
        nth_int, nth_bit = divmod(index, BitArray._SIZE_OF_INT_IN_BITS)
        return self.bits[nth_int] & (1 << nth_bit)

    def __setitem__(self, index, fill):
        """Set the bit at ``index`` to ``fill``.

        :param fill: 0 or 1.
        """
        nth_int, nth_bit = divmod(index, BitArray._SIZE_OF_INT_IN_BITS)
        if fill:
            self.bits[nth_int] |= (1 << nth_bit)
        else:
            self.bits[nth_int] &= ~(1 << nth_bit)
