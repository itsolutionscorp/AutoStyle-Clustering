def detect_anagrams(word, possibleAnagrams):
    anagrams = []
    for elem in possibleAnagrams:
        if sorted(elem.lower()) == sorted(word.lower()) and elem.lower() != word.lower():
            anagrams.append(elem)
    return anagrams
    
            
