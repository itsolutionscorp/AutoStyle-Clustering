def decreaser(closest):
    decrease = 0
    if closest == 5 or closest == 10:
        decrease = 1
    elif closest == 50 or closest == 100:
        decrease = 10
    elif closest == 500 or closest == 1000:
        decrease = 100
    return decrease

def numeral(norm):
    count = norm
    roman = ""
    
    dict = {1: 'I',
            5: 'V',
            10: 'X',
            50: 'L',
            100: 'C',
            500: 'D',
            1000: 'M'
    }
    arabic = dict.keys()
    arabic.sort()
    
    while count > 0:
        closest = min(arabic, key=lambda k: abs(k-count))
        print closest, count
        if closest == count:
            roman += dict[closest]
            count -= closest
        elif closest > count:
            decrease = decreaser(closest)
            if count == 8:
                roman += dict[5]
                roman += dict[1]
                roman += dict[1]
                roman += dict[1]
            else:
                roman += dict[decrease]
                roman += dict[closest]
            count -= closest - decrease
        elif closest < count:
            roman += dict[closest]
            count -= closest
            
            
                
    return roman
    

    
