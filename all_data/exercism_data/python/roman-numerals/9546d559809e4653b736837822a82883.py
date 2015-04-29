from math import log,floor

# Group the roman numerals according to 'one' or 'five', for use in _to_roman()
num_map = {
    1:('I','X','C','M'),
    5:('V','L','D')
}

def numeral(arabnum):
    '''Compute recursively the Roman numeral representation for each digit, left to right.'''
    
    if arabnum > 3000:
        raise ValueError('Number must be less than 3000.')
    elif not arabnum:
        return ''
    else:
        highest_power = int(floor(log(arabnum,10)))
        q,r = divmod(arabnum, 10**highest_power)
        return _to_roman(q,highest_power) + numeral(r)

def _to_roman(m, n):
    '''Convert a power-of-ten number m*10**n to Roman numeral.'''

    q,r = divmod(m,5)

    if r < 4:
        return (num_map[5][n] if q else '') + num_map[1][n]*r
    else:
        return num_map[1][n] + (num_map[1][n+1] if q else num_map[5][n])
