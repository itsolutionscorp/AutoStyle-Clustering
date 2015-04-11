def detect_anagrams(check_word, possibles):
    check = ''.join(sorted(check_word.upper()))
    anagrams = []

    for words in possibles:
        this_word = ''.join(sorted(words.upper()))
        
        if this_word == check and words.upper() != check_word.upper():
            anagrams.append(words)
        

    return anagrams
            
            
