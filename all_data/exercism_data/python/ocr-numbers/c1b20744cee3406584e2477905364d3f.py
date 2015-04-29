def grid( N ):
    if int(N) not in {0,1}:
        raise ValueError( "Grid can only see 0 or 1!" )
    if int(N) == 0:
        return [
            " _ ",
            "| |",
            "|_|",
            "   "]
    elif int(N) == 1:
        return [
            "   ",
            "  |",
            "  |",
            "   "]

def number( g ):
    if (not len(g) == 4) or any(not len(r)==3 for r in g):
        raise ValueError( "Invalid grid!" )
    for N in {0,1}:
        if g == grid(str(N)):
            return str(N)
    return '?'
