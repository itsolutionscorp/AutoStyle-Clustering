def numeral(arabic):
    roman = ""
    value_places = "IXCM"
    value_halves = "VLD"
    
    
    power = 4
    while power:
        power -= 1
        value = arabic / 10**power
        
        if value==9:
            roman += value_places[power]
            roman += value_places[power+1]
        elif value==4:
            roman += value_places[power]
            roman += value_halves[power]
        else:
            if value>=5:
                roman += value_halves[power]
                value -= 5
            roman += value_places[power]*value
            
        arabic %= 10**power
    
    return roman
