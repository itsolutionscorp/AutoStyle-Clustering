ocr_nums = {'0':[" _ ", "| |", "|_|", "   "],'1':["   ", "  |", "  |", "   "],
            '2':[" _ ", " _|", "|_ ", "   "],'3':[" _ ", " _|", " _|", "   "],
            '4':["   ", "|_|", "  |", "   "],'5':[" _ ", "|_ ", " _|", "   "],
            '6':[" _ ", "|_ ", "|_|", "   "],'7':[" _ ", "  |", "  |", "   "],
            '8':[" _ ", "|_|", "|_|", "   "],'9':[" _ ", "|_|", " _|", "   "]
            } 

def number(ONUM):
    if len(ONUM) != 4:
        raise ValueError
    for i in ONUM:
        if len(i) != 3:
            raise ValueError 
    for k, v in ocr_nums.items():
        if ONUM == v:
            return k
    return '?'
    
def grid(n):
    for k, v in ocr_nums.items():
        if n == k:
            return v
