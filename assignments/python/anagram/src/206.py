def detect_anagrams(word, possibleAnagrams):
    w = sorted(word.lower())
    anagrams = []
    for elem in possibleAnagrams:
        if sorted(elem.lower()) == w and elem.lower() != word.lower():
            anagrams.append(elem)
    return anagrams
    
            
