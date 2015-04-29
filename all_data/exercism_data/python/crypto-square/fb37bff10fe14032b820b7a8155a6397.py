"""A square code."""

from itertools import chain, ifilter, izip, izip_longest
from math import sqrt
import numbers
import string


PUNCSPACE = frozenset(string.punctuation + string.whitespace)


def encode(plaintext):
    """Encode plaintext by a square code."""
    # plaintext =
    #   "If man was meant to stay on the ground god would have given us roots"

    plaintext = [char.lower() for char in plaintext if char not in PUNCSPACE]
    # plaintext = ["i", "f", "m", ... "t", "s"]
    # len(plaintext) = 54

    ncolumns = isqrt_gte(len(plaintext))
    # ncolumns = 8

    rows = grouper(plaintext, ncolumns, fillvalue=None)
    # rows = [
    #   ("i", "f", "m", "a", "n", "w", "a", "s"),
    #   ("m", "e", "a", "n", "t", "t", "o", "s"),
    #   ...
    #   ("v", "e", "g", "i", "v", "e", "n", "u"),
    #   ("s", "r", "o", "o", "t", "s", None, None)
    # ]

    columns = izip(*rows)
    # columns = [
    #   ("i", "m", "t", "g", "d", "v", "s"),
    #   ("f", "e", "a", "r", "w", "e", "r"),
    #   ...
    #   ("a", "o", "h", "g", "h", "n", None),
    #   ("s", "s", "e", "o", "a", "u", None)
    # ]

    ciphertext = ifilter(lambda char: char is not None, chain(*columns))
    # ciphertext = ["i", "m", "t", "g", "d", "v", ..., "a", "u"]

    blocks = grouper(ciphertext, 5, fillvalue="")
    # blocks = [
    #   ("i", "m", "t", "g", "d"),
    #   ("v", "s", "f", "e", "a"),
    #   ...
    #   ("g", "h", "n", "s", "s"),
    #   ("e", "o", "a", "u", "")
    # ]

    return " ".join("".join(block) for block in blocks)
    # " ".join("".join(block) for block in blocks) =
    #   "imtgd vsfea rwerm ayoog oanou uiont nnlvt wttdd esaoh ghnss eoau"


def decode(ciphertext):
    """Decode ciphertext by a square code."""
    # ciphertext =
    #   "imtgd vsfea rwerm ayoog oanou uiont nnlvt wttdd esaoh ghnss eoau"

    ciphertext = [char for char in ciphertext if char not in PUNCSPACE]
    # ciphertext = ["i", "m", "t", "g", "d", "v", ..., "a", "u"]
    # len(ciphertext) = 54

    ncolumns = isqrt_gte(len(ciphertext))
    # ncolumns = 8
    nrows = (len(ciphertext) + ncolumns - 1) // ncolumns
    # nrows = 7
    nblanks = ncolumns * nrows - len(ciphertext)
    # nblanks = 2

    chars = iter(ciphertext)
    columns = []
    for _ in xrange(ncolumns - nblanks):  # columns without blanks (i.e. None)
        column = [next(chars) for _ in xrange(nrows)]
        columns.append(column)
    for _ in xrange(ncolumns - nblanks, ncolumns):  # columns with blanks
        column = [next(chars) for _ in xrange(nrows - 1)]
        column.append(None)
        columns.append(column)
    assert next(chars, None) is None
    # columns = [
    #   ["i", "m", "t", "g", "d", "v", "s"],
    #   ["f", "e", "a", "r", "w", "e", "r"],
    #   ...
    #   ["a", "o", "h", "g", "h", "n", None],
    #   ["s", "s", "e", "o", "a", "u", None]
    # ]

    rows = izip(*columns)
    # rows = [
    #   ("i", "f", "m", "a", "n", "w", "a", "s"),
    #   ("m", "e", "a", "n", "t", "t", "o", "s"),
    #   ...
    #   ("v", "e", "g", "i", "v", "e", "n", "u"),
    #   ("s", "r", "o", "o", "t", "s", None, None)
    # ]

    plaintext = ifilter(lambda char: char is not None, chain(*rows))
    # plaintext = ["i", "f", "m", ..., "t", "s"]

    return "".join(plaintext)
    # "".join(plaintext) =
    #   "ifmanwasmeanttostayonthegroundgodwouldhavegivenusroots"


def isqrt_gte(n):
    """Return the smallest integer greater than or equal to the square root.

    :param n: a nonnegative integer (n >= 0)
    """
    if n == 0: return 0
    try:
        ret = int(sqrt(n))
    except OverflowError:
        # Fall back on Newton's method.
        ret = isqrt_lte(n)
    while ret * ret >= n: ret -= 1
    ret += 1
    while ret * ret < n: ret += 1
    return ret


def isqrt_lte(n):
    """Return the greatest integer less than or equal to the square root.

    :param n: a nonnegative integer (n >= 0)
    """
    if n == 0: return 0
    q, r = divmod(n.bit_length(), 2)
    x = 2 ** (q + r)
    while True:
        y = (x + n // x) >> 1
        if y >= x:
            return x
        x = y


def grouper(iterable, n, fillvalue=None):
    """Collect data into fixed-length chunks or blocks."""
    # grouper("ABCDEFG", 3, "x") --> ABC DEF Gxx
    return izip_longest(*([iter(iterable)] * n), fillvalue=fillvalue)
