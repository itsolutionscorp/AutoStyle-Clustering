_NUMERAL_SET = ( (1000 , 'M'),
                 (900  , 'CM'),
                 (500  , 'D'),
                 (400  , 'CD'),
                 (100  , 'C'),
                 (90   , 'XC'),
                 (50   , 'L'),
                 (40   , 'XL'),
                 (10   , 'X'),
                 (9    , 'IX'),
                 (5    , 'V'),
                 (4    , 'IV'),
                 (1    , 'I'),
                )

def numeral(arabic):
    return _a_2_n(arabic, '', iter(_NUMERAL_SET))

def _a_2_n(arabic, roman, numeral_iter):
    try:
        div, num_code = next(numeral_iter)
    except StopIteration:
        return roman
    else:
        roman += num_code*(arabic // div)
        return _a_2_n(arabic % div, roman, numeral_iter)
