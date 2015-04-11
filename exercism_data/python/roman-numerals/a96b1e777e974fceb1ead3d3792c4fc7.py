def numeral(arabic):
    roman = ""
    if arabic > 3000:
        roman = "number too large"
    else:
        count = 1
        for i in reversed(str(arabic)):
            if count == 1:
                roman = subroman(int(i), "IIIVIIIX") + roman
            elif count == 2:
                roman = subroman(int(i), "XXXLXXXC") + roman
            elif count == 3:
                roman = subroman(int(i), "CCCDCCCM") + roman
            elif count == 4:
                roman = "M" * int(i) + roman
            count += 1
    return roman
    
def subroman(a, rs):
    if a == 0:
        return ""
    elif a == 1:
        return rs[0:1:]
    elif a == 2:
        return rs[0:2:]
    elif a == 3:
        return rs[0:3:]
    elif a == 4:
        return rs[2:4:]
    elif a == 5:
        return rs[3:4:]
    elif a == 6:
        return rs[3:5:]
    elif a == 7:
        return rs[3:6:]
    elif a == 8:
        return rs[3:7:]
    elif a == 9:
        return rs[6:8:]
