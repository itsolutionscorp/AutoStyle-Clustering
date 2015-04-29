"""Secret Handshake"""

BIT_TO_GESTURE = (
    (0b1, "wink"),
    (0b10, "double blink"),
    (0b100, "close your eyes"),
    (0b1000, "jump")
)

REVERSE_BIT = 0b10000

#: a number with all possible bits turned on
MAX = reduce(lambda acc, bit: acc | bit,
             (bit for bit, _ in BIT_TO_GESTURE), REVERSE_BIT)


def handshake(number):
    """Convert a number into a list of gestures.

    :return: a list of gestures if possible, otherwise []
    """
    if isinstance(number, str):
        try:
            number = to_int(number, base=2)
        except ValueError:
            number = 0
    if not (0 <= number <= MAX):
        number = 0

    gestures = []
    for bit, ges in BIT_TO_GESTURE:
        if number & bit:
            gestures.append(ges)
    if number & REVERSE_BIT:
        gestures.reverse()
    return gestures


def code(gestures):
    """Convert a sequence of gestures into a binary representation.

    :return: a binary representation if possible, otherwise "0"
    """
    number = _code(gestures) or _code(gestures, reverse=True)
    return number if number is not None else "0"


def _code(gestures, reverse=False):
    """Convert a sequence of gestures into a binary representation.

    :param reverse: whether the order of the gestures is reversed
    :return: a binary representation if possible, otherwise None
    """
    if reverse:
        number = REVERSE_BIT
        gestures = reversed(gestures)
    else:
        number = 0

    try:
        bit_gesture = iter(BIT_TO_GESTURE)
        bit, ges = next(bit_gesture)
        for gesture in gestures:
            while gesture != ges:
                bit, ges = next(bit_gesture)
            number |= bit
        return from_int(number, base=2)
    except StopIteration:
        return None


def to_int(digits, base=10):
    """Convert an integer representation in the given base to an integer.

    :param digits: a nonnegative integer representation
    :type digits: str
    :param base: a number base (2 <= base <= 10)
    :type base: int
    :rtype: int
    """
    zero = ord('0')
    valid_digits = [chr(n) for n in xrange(zero, zero + base)]
    number = 0
    for digit in digits:
        if digit not in valid_digits:
            raise ValueError(digit)
        number = number * base + (ord(digit) - zero)
    return number


def from_int(number, base=10):
    """Convert an integer to an integer representation in the given base.

    :param number: a nonnegative integer
    :type number: int
    :param base: a number base (2 <= base <= 10)
    :type base: int
    :rtype: str
    """
    digits = []
    quotient, remainder = divmod(number, base)
    digits.append(str(remainder))
    while quotient > 0:
        quotient, remainder = divmod(quotient, base)
        digits.append(str(remainder))
    digits.reverse()
    return "".join(digits)
