# William Morris
# exercism.io
# roman.py

def num_builder(num,num_above, half_num, base_num):
    if num == 9:
        return base_num + num_above
    elif num == 4:
        return base_num + half_num
    else:
        sub_string = ''
        halfway = num // 5
        base = num % 5
        if halfway:
            sub_string += half_num
        for n in range(base):
            sub_string += base_num
        return sub_string
            
def numeral(arabic):
    if arabic > 3000 or arabic < 1:
        raise ValueError('arabic number out of range')
    roman = ''
    mili = arabic // 1000
    roman += num_builder(mili,None, None,'M')
    arabic %= 1000
    centi =  arabic // 100
    roman += num_builder(centi,'M','D','C')
    arabic %= 100
    deci = arabic // 10
    roman += num_builder(deci,'C','L','X')
    mono = arabic % 10
    roman += num_builder(mono,'X','V','I')
    return roman

    
