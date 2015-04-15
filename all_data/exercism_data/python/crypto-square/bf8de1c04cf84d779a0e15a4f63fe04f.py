import re
from itertools import izip_longest
from string import whitespace, punctuation
import numpy as np

_PATTERN = '[{0}{1}]'.format(whitespace, punctuation)
REGEX = re.compile(_PATTERN)


def _prep_string(s):
    cleaned, _ = REGEX.subn('', s.lower())
    N = len(cleaned)
    n = np.ceil(np.sqrt(N))
    pad = ' ' * (n * n - N)
    return np.fromiter(cleaned + pad, dtype='S1').reshape(n, n)


def encode(s):
    arr = _prep_string(s)
    msg = ''.join(arr.flatten('F').tolist()).replace(' ', '')
    return ' '.join(''.join(t) for t in izip_longest(*[iter(msg)] * 5,
                                                     fillvalue=' ')).strip()


def decode(s):
    sp = _prep_string(s)
    space_indexes = np.where(sp.T.flatten() == ' ')[0]
    l = list(s.replace(' ', ''))
    for i in space_indexes:
        l.insert(i, ' ')
    return ''.join(np.array(l).reshape(sp.shape).flatten('F')).strip()
