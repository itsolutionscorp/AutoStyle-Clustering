scores = {
    "AEIOULNRST": 1,
    "DG": 2,
    "BCMP": 3,
    "FHVWY": 4,
    "K": 5,
    "JX": 8,
    "QZ": 10}


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
