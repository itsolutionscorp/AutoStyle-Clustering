from re import match

def parse_binary( N ):
    if not match("^[0-1]*$",N):
        raise ValueError( "Not a valid binary string!" )
    return sum([ int(N[ii]) * (2**(len(N)-ii-1)) for ii in range(len(N)) ])
