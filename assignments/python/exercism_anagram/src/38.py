from collections import Counter
def detect_anagrams(word, possibles):
    out = []
    
    for p in possibles:
        if Counter(word.lower()) == Counter(p.lower()):
            if not p.lower()==word.lower():
                out.append(p)
    return out
