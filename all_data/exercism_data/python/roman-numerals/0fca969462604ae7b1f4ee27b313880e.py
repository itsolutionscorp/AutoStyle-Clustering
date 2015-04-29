def numeral(arabic_num):
    counter = arabic_num
    result = ''
    
    numeral_map = {
        1000:'M',900:'CM',500:'D',400:'CD',
        100:'C',90:'XC',50:'L',40:'XL',
        10:'X',9:'IX',5:'V',4:'IV',1:'I'}
    sorted_numbers = sorted(numeral_map,reverse=True)
    
    for number in sorted_numbers:
        while counter >= number:
            result += numeral_map[number]
            counter -= number
    return result
