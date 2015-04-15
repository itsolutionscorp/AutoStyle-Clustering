values = {
    '1' : ('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'),
    '2' : ('D', 'G'),
    '3' : ('B', 'C', 'M', 'P'),
    '4' : ('F', 'H', 'V', 'W', 'Y'),
    '5' : ('K'),
    '8' : ('J', 'X'),
    '10': ('Q', 'Z')
    }

def score(word):
    
    if not word:
        return 0
    else:
        letter_scores = [k for k, v in values.iteritems()
                         for x in word if x.upper() in v]
                         
        return sum([int(d) for d in letter_scores])
