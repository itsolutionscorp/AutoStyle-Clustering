def numeral(arabic_num):
    result = ''
    numeral_map = {1000:'M',900:'CM',500:'D',400:'CD',
                   100:'C',90:'XC',50:'L',40:'XL',
                   10:'X',9:'IX',5:'V',4:'IV',1:'I'}
    sorted_numbers = sorted(numeral_map,reverse=True)
    
    for high_number in sorted_numbers:
        while arabic_num >= high_number:
            result += numeral_map[high_number]
            arabic_num -= high_number
    return result
