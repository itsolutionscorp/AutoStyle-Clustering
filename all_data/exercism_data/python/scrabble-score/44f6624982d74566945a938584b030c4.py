values = {
    'A':1, 'E':1, 'I':1, 'O':1, 'U':1, 'L':1, 'N':1, 'R':1, 'S':1, 'T':1,
    'D':2, 'G':2,
    'B':3, 'C':3, 'M':3, 'P':3,
    'F':4, 'H':4, 'V':4, 'W':4, 'Y':4,
    'K':5,
    'J':8, 'X':8,
    'Q':10, 'Z':10
    }

def score(word, dbl_let='', trp_let='', dbl_word=False, trp_word=False):
    
    if not word:
        return 0
    else:
        letter_scores = [v for k, v in values.iteritems()
                         for x in word if x.upper() == k]
                         
        word_score_base = sum([int(d) for d in letter_scores])
                       
        for x in dbl_let:
            word_score_base += values[x]
        for x in trp_let:
            word_score_base += values[x] * 2
        if dbl_word == True:
            word_score_base *= 2
        if trp_word == True:
            word_Score_base *= 3    
                         
        return word_score_base
