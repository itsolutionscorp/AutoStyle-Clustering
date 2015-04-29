_NUMBERS = {
    0o020101121000: '0',
    0o000001001000: '1',
    0o020021120000: '2',
    0o020021011000: '3',
    0o000121001000: '4',
    0o020120021000: '5',
    0o020120121000: '6',
    0o020001001000: '7',
    0o020121121000: '8',
    0o020121021000: '9',
}

_GRIDS = {v: k for k, v in _NUMBERS.items()}  # py3.4

_CHARS = {' ': '0', '|': '1', '_': '2'}


def _check_grd(grd):
    if len(grd) != 4 or not all(len(row) == 3 for row in grd):
        raise ValueError("Input missing line.")


def number(grd):
    _check_grd(grd)
    try:
        s = ''.join(_CHARS[c] for row in grd for c in row)
    except KeyError:
        return '?'
    return _NUMBERS.get(int(s, 8), '?')


def grid(n):
    _chars = ' |_'
    try:
        num = _GRIDS[n]
    except KeyError:
        raise ValueError("Invalid character in input")
    res = [_chars[(num >> i) & 0o7] for i in range(33, -1, -3)]
    return [''.join(t) for t in zip(*[iter(res)] * 3)]
