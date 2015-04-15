def numeral(arabic):
    if arabic >= 4000:
        raise ValueError("Can't convert numbers over 4000")
    return ''.join([convert(str(arabic)[i], len(str(arabic))-i) for i in xrange(len(str(arabic)))])


def convert(digit, position):
    digit = int(digit)
    if position == 4:
      return 'M'*digit
    elif position >= 3:
        numerals = ['M','D','C']
    elif position == 2:
        numerals = ['C', 'L', 'X']
    else:
        numerals = ['X', 'V', 'I']

    if digit == 9:
        return numerals[2] + numerals[0]
    elif digit >= 5:
        return numerals[1] + numerals[2]*(digit - 5)
    elif digit == 4:
        return numerals[2]+numerals[1]
    else:
        return numerals[2]*digit
