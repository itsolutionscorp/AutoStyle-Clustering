numerals = {
    1: 'I',
    5: 'V',
    10: 'X',
    50: 'L',
    100: 'C',
    500: 'D',
    1000: 'M'
}

def numeral(arabic):
    digits = getdigits(arabic)
    roman = []

    for i, d in enumerate(digits):
        if not d:
            continue

        key = 10 ** (len(digits) - i - 1)
        val = d * key
        count = val / key

        if count < 4:
            roman.append(''.join([numerals[key]] * count))
            continue

        nkey = min(k for k in numerals.keys() if k > key)

        if count < 6:
            less = (nkey - val) / key
            roman.append(''.join([numerals[key]] * less + [numerals[nkey]]))
        elif count < 9:
            more = (val - nkey) / key
            roman.append(''.join([numerals[nkey]] + [numerals[key]] * more))
        else:
            nkey2 = min(k for k in numerals.keys() if k > nkey)
            less = (nkey2 - val) / key
            roman.append(''.join([numerals[key]] * less + [numerals[nkey2]]))

    return ''.join(roman)

def getdigits(num):
    return [num] if num < 10 else getdigits(num / 10) + [num % 10]
