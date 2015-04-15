romans = [(1,'I'), (5,'V'), (10,'X'), (50,'L'), (100,'C'), (500,'D'), (1000,'M')]

def numeral(arabic_num):
    ret_str = ''
    while arabic_num != 0:
        print arabic_num
        for i,tup in enumerate(romans[::-1]):
            val = tup[0]
            letter = tup[1]
            next_val = None
            next_letter = None
            if i + 1 < len(romans):
                next_val = romans[::-1][i + 1][0]
                next_letter = romans[::-1][i + 1][1]

            if arabic_num >= val:
                arabic_num -= val
                ret_str += letter
                break
            elif next_val is not None and arabic_num >= val - next_val:
                print val, next_val, arabic_num
                arabic_num -= (val - next_val)
                ret_str += next_letter + letter
                break
            elif arabic_num == 1:
                ret_str += 'I'
                arabic_num -= 1

    return ret_str

            
