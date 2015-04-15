letters = {'A':10, 'B':11, 'C':12, 'D':13, 'E':14, 'F':15}

def hexa(num):
    try:
        lst = []
        n = 1
        for i in num[::-1]:
            if i.isdigit():
                lst.append(int(i)*16**(n-1))
            elif i.isalpha():
                lst.append(letters.get(i.upper())*16**(n-1))
            n += 1
        return sum(lst)
    except:
        raise ValueError
        
#would like to incorporate regex for the check at some point
