# define mapping dictionary
# note additional entries for 4, 9, 40, 90, 400 and 500
arabic_to_roman = {1: 'I', 5: 'V', 10: 'X', 50: 'L', 100: 'C', 500: 'D', 1000: 'M', 4: 'IV', 9: 'IX', 40: 'XL', 90: 'XC', 400: 'CD', 900: 'CM'}

def numeral(num):
    # init vars
    num_copy = num
    result = ''
    while num_copy > 0:
        # go through each key largest to smallest
        for key in sorted(arabic_to_roman, reverse=True):
            # once you get to a key that fits in num_copy
            if num_copy // key > 0:
                # add that roman numeral to the result
                result += arabic_to_roman[key]
                # take that amount away from num_copy
                num_copy -= key
                break
    return result
