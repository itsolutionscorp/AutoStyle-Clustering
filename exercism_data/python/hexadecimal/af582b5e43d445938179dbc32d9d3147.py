hexkey = dict(zip('0123456789ABCDEF', range(16)))

def hexa(hexes):
    dec = 0
    for h in hexes:
        dec *= 16
        try:        
            dec += hexkey[h.upper()]
        except KeyError:
            raise ValueError('Not a valid hex string.')    
    return dec
