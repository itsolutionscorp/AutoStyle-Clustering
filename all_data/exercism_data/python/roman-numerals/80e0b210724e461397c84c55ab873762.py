# William Morris
# exercism.io
# roman.py

def num_builder(num,ten_num, five_num, base_num):
    if num == 9:
        return base_num + ten_num
    elif num == 4:
        return base_num + five_num
    else:
        sub_string = ''
        halfway = num // 5
        base = num % 5
        if halfway:
            sub_string += five_num
        for n in range(base):
            sub_string += base_num
        return sub_string
            
def numeral(arabic):
    if arabic > 3000 or arabic < 1:
        raise ValueError('arabic number out of range')
    roman = ''
    nums = [None, None,'M','D','C','L','X','V','I']
    
    for i in range(4):
        nth_power = 3 - i
        left_most = arabic // 10**nth_power
        roman += num_builder(left_most,nums[i*2],nums[i*2+1],nums[i*2+2])
        arabic %= 10**nth_power
    return roman

    
