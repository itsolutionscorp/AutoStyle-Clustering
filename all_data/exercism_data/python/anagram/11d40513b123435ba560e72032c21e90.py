from itertools import permutations

def detect_anagrams(word, possibles):
    # create list of anagrams
    anagrams = list(set([''.join(p) for p in permutations(word.lower(), len(word))]))
        
    # check for anagrams in possibles
    results = []
    for p in possibles:
        if p.lower() in anagrams and p.lower() != word.lower():
            results.append(p)
            
    return results
