from re import match

def hexa( N ):
    return sum( ['0123456789abcdef'.index(N[::-1][ii].lower())*16**ii
                 for ii in range(len(N))] )
