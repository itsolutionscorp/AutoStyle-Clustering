def numeral(arabic):
    one = 'I', 'X', 'C', 'M'
    five = 'V', 'L', 'D'

    result = []

    for i, digit in enumerate(reversed(str(arabic))):
        digit = int(digit)

        if digit == 0:
            s = ''
        elif 1 <= digit <= 3:
            s = digit * one[i]
        elif digit == 4:
            s = one[i] + five[i]
        elif digit == 5:
            s = five[i]
        elif 6 <= digit <= 8:
            s = five[i] + (digit - 5) * one[i]
        elif digit == 9:
            s = one[i] + one[i + 1]

        result.append(s)

    return ''.join(reversed(result))
