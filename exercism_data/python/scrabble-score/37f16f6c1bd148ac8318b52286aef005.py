scores = {
    ('A', 'E','I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'): 1,
    ('D', 'G'): 2,
    ('B', 'C', 'M', 'P'): 3,
    ('F', 'H', 'V', 'W', 'Y'): 4,
    ('K'): 5,
    ('J', 'X'): 8,
    ('Q', 'Z'): 10}


def score(word):
    if word.strip() == "":
        return 0
        
    word = word.upper()
    word_score = 0
    
    for c in word:
        for k, v in scores.items():
            if c in k:
                word_score += v
                
    return word_score
