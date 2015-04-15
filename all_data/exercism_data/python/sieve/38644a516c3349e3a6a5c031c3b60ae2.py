"""The Sieve of Eratosthenes."""

import array
import math
import struct


def prime_numbers(upto):
    """ Generate prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    """
    is_prime = BitArray(upto + 1, fill=1)
    # 2 <= number < isqrt(upto) + 1
    for number in xrange(2, isqrt(upto) + 1):
        if is_prime[number]:
            yield number
            # 2 * number, 3 * number, ..., (number - 1) * number have already
            # been marked as non-prime numbers because they are multiples of 2,
            # 3, ..., number - 1.
            multiples = xrange(number ** 2, upto + 1, number)
            for multiple in multiples:
                is_prime[multiple] = 0
    # isqrt(upto) + 1 <= number < upto + 1
    for number in xrange(number + 1, upto + 1):
        if is_prime[number]:
            yield number
    return


def sieve(upto):
    """ Return a list of prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    :returns: a list of prime numbers up to a given number.
    :rtype: list of int.
    """
    return list(prime_numbers(upto))


def isqrt(n):
    """Return the greatest integer less than or equal to the square root.

    :param n: a nonnegative integer (n >= 0)
    """
    ret = int(math.sqrt(n))
    ret += 1
    while ret * ret <= n: ret += 1
    ret -= 1
    while ret * ret > n: ret -= 1
    return ret


class BitArray(object):
    """A memory-efficient bit array."""

    _SIZE_OF_SHORT_IN_BITS = struct.calcsize("H") * 8

    def __init__(self, nbits, fill=0):
        """Create a bit array of a given size filled with zeros or ones.

        :param nbits: the size of an array in bits.
        :param fill: 0 or 1.
        """
        if nbits % BitArray._SIZE_OF_SHORT_IN_BITS == 0:
            nshorts = nbits // BitArray._SIZE_OF_SHORT_IN_BITS
        else:
            nshorts = (nbits // BitArray._SIZE_OF_SHORT_IN_BITS) + 1
        fill = (2 ** BitArray._SIZE_OF_SHORT_IN_BITS - 1) if fill else 0
        # We use unsigned short ("H") because the values stored for C's
        # unsigned (long) integers are represented as Python long integers when
        # retrieved: Python's plain integer type cannot represent the full
        # range of C's unsigned (long) integers.
        self.bits = array.array("H")
        self.bits.extend(fill for _ in xrange(nshorts))

    def __getitem__(self, index):
        """Get the bit at ``index``."""
        nth_short, nth_bit = divmod(index, BitArray._SIZE_OF_SHORT_IN_BITS)
        return self.bits[nth_short] & (1 << nth_bit)

    def __setitem__(self, index, fill):
        """Set the bit at ``index`` to ``fill``.

        :param fill: 0 or 1.
        """
        nth_short, nth_bit = divmod(index, BitArray._SIZE_OF_SHORT_IN_BITS)
        if fill:
            self.bits[nth_short] |= (1 << nth_bit)
        else:
            self.bits[nth_short] &= ~(1 << nth_bit)
