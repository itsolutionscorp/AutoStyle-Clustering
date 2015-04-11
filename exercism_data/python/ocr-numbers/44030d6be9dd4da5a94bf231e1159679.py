'''exer ocr'''
           #  0      1      2      3     4       5      6      7
SEGMENTS = ['   ', '|  ', ' _ ', '|_ ', '  |', '| |', ' _|', '|_|']
CHARS = {'0': '2570', '1': '0440', '2': '0263', '3': '2660', '4': '0740',
         '5': '2360', '6': '2370', '7': '2440', '8': '2770', '9': '2740'}

def grid(digit):
    '''return the segments needed for string of digits in digit'''
    output = []
    for char in digit:
        try:
            for segment in CHARS[char]:
                output.append(SEGMENTS[int(segment)])
        except KeyError:
            raise ValueError("Unknown symbol: %s" % char)
    return output

def number(grid_input):
    '''decipher grid input back to digit string'''
    # most of the code here is just guard code
    _number = ''
    gmap = ''

    # guard not enough rows
    if len(grid_input) % len(CHARS['0']):
        raise ValueError('Too Few Rows')

    for row in grid_input:
        # guard row length
        if len(row) != len(SEGMENTS[0]):
            raise ValueError('Row length too short')

        # guard unknown segment
        if row not in SEGMENTS:
            return '?'
        # build segment map
        gmap += str(SEGMENTS.index(row))

    # transcode segment map to represented char
    for key, val in CHARS.iteritems():
        if val == gmap:
            _number += key
    # guard against valid segments but unknown CHAR
    if not _number:
        _number = '?'

    return _number
