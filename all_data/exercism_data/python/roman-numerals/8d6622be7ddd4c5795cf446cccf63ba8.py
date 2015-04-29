SYMBOLS = "IVXLCDM"
#NOTE: patterns are reversed
PATTERNS = [[], [0], [0,0], [0,0,0], [1, 0], [1], [0, 1], [0, 0, 1], [0, 0, 0, 1], [2, 0]]

def digits(n):
    while n:
        n, r = divmod(n, 10)
        yield(r)

def numeral(n):
    if not (0 < n < 4000):
         raise ValueError("Argument must be between 1 and 3999")
     
    result = [SYMBOLS[sym + i*2] for i, d in enumerate(digits(n)) for sym in PATTERNS[d]]
    return "".join(reversed(result))
