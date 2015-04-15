_NUMERAL_DICT = { 1000 : 'M',
                 900  : 'CM',
                 500  : 'D',
                 400  : 'CD',
                 100  : 'C',
                 90   : 'XC',
                 50   : 'L',
                 40   : 'XL',
                 10   : 'X',
                 9    : 'IX',
                 5    : 'V',
                 4    : 'IV',
                 1    : 'I',
                }

def numeral(arabic):
    return _arabic_2_numeral(arabic, iter(sorted(_NUMERAL_DICT, reverse=True)))

def _arabic_2_numeral(arabic, numeral_iter):
    try:
        div = next(numeral_iter)
    except StopIteration:
        return ''
    else:
        numeral = _NUMERAL_DICT.get(div) * (arabic // div)
        return numeral + _arabic_2_numeral(arabic % div, numeral_iter)
