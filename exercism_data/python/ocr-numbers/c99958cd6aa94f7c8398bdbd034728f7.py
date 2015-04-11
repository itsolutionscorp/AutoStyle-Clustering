def grid(digit):
    if digit == '0':
        return [' _ ', '| |', '|_|', '   ']
    elif digit == '1':
        return ['   ', '  |', '  |', '   ']
    else:
        raise ValueError('Digit not supported.')


def number(rows):
    if len(rows) != 4:
        raise ValueError('Exactly 4 rows expected.')
    if not all(len(row) == 3 for row in rows):
        raise ValueError('All rows expected to have length 3.')

    for digit in ('0', '1'):
        if grid(digit) == rows:
            return digit
    return '?'
