def subnumeral(place, letters):
    numeral = dict(zip(range(10), ('', 'I', 'II', 'III', 'IV', 'V', 'VI',
                                   'VII', 'VIII', 'IX')))[place]
    transtable = str.maketrans('IVX', letters)
    return numeral.translate(transtable)

def numeral(n):
    thousands, hundreds, tens, ones = [int(c) for c in str(n).zfill(4)]
    return '{}{}{}{}'.format(thousands * 'M', subnumeral(hundreds, 'CDM'),
                             subnumeral(tens, 'XLC'), subnumeral(ones, 'IVX'))
