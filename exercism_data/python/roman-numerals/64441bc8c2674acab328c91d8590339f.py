numeral_pat = ['',
               '{0}',
               '{0}{0}',
               '{0}{0}{0}',
               '{0}{1}',
               '{1}',
               '{1}{0}',
               '{1}{0}{0}',
               '{1}{0}{0}{0}',
               '{0}{2}']
numerals = {0: ['I', 'V', 'X'],
            1: ['X', 'L', 'C'],
            2: ['C', 'D', 'M']}

def numeral(arabic):
    if arabic > 3000:
        raise ValueError('Error: number must be less than 3000')
    arabic = map(int, list(reversed(str(arabic))))
    numeral = ''
    for i in range(0, min(3, len(arabic))):
        numeral = numeral_pat[arabic[i]].format(*numerals[i]) + numeral
    if len(arabic) == 4:
        numeral = 'M' * arabic[3] + numeral
    return numeral
