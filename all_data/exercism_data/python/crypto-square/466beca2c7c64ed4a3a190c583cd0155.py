import math
import re
invalid_chars = re.compile(r"[^a-z0-9]")

def encode(s):
    clean = invalid_chars.sub("", s.lower())
    size = int( math.ceil( math.sqrt( len(clean) ) ) )

    encoded = "".join( clean[ i : len(clean) : size ] for i in range(size) )

    return " ".join( encoded[ i : i+5 ] for i in xrange(0, len(encoded), 5) )

def decode(s):
    clean = invalid_chars.sub("", s.lower())
    size = int( math.ceil( math.sqrt( len(clean) ) ) )

    # text may not be a perfect square, making some columns in the original taller than others
    base, rem = divmod( len(clean), size )
    bigger_end = rem * (base + 1)

    # get the characters from the taller columns first, then the shorter columns
    bigger_columns = [ clean[ i : bigger_end : base+1 ] for i in range(base + 1) ]
    # need the additional empty string here for zip
    smaller_columns = [ clean[ bigger_end+i : len(clean) : base ] for i in range(base) ] + [ "" ]

    # finally, join everything together
    return "".join( "".join(t) for t in zip(bigger_columns, smaller_columns) )
