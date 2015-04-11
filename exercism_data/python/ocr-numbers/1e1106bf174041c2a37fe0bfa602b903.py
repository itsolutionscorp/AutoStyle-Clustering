digits = [[" _ ",
           "| |",
           "|_|",
           "   "],
          ["   ",
           "  |",
           "  |",
           "   "]]
          #[" _ ",
           #" _|",
           #"|_ ",
           #"   "],
          #[" _ ",
           #" _|",
           #" _|",
           #"   "],
          #["   ",
           #"|_|",
           #"  |",
           #"   "],
          #[" _ ",
           #"|_ ",
           #" _|",
           #"   "],
          #[" _ ",
           #"|_ ",
           #"|_|",
           #"   "],
          #[" _ ",
           #"  |",
           #"  |",
           #"   "],
          #[" _ ",
           #"|_|",
           #"|_|",
           #"   "],
          #[" _ ",
           #"|_|",
           #" _|",
           #"   "]]


def number(s):
    if len(s) == 4 and all(len(j) == 3 for j in s):
        for i in xrange(len(digits)):
            if s == digits[i]:
                return str(i)
        return '?'
    raise ValueError('Ill-formed grid')


def grid(n):
    num = ord(n) - ord('0')
    if num < 0 or num >= len(digits):
        raise ValueError('Unknown digit')
    return digits[num]
