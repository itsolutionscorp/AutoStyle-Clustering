def numeral(n):
    letters =   {   1   :   'I',\
                    4   :   'IV',\
                    5   :   'V',\
                    9   :   'IX',\
                    10  :   'X',\
                    40  :   'XL',\
                    50  :   'L',\
                    90  :   'XC',\
                    100 :   'C',\
                    400 :   'CD',\
                    500 :   'D',\
                    900 :   'CM',\
                    1000:   'M' }
    reverse = reversed(sorted(letters.keys()))
    ans = ""
    if not n:
        return ""
    for i in reverse:
        if n - i >= 0:
            ans = letters[i] + numeral(n-i)
            return ans
    
