def detect_anagrams(reference, word_list):
    
    reference_list = sorted(reference.casefold())
    
    return [word
            for word
            in word_list
            if word.casefold() != reference.casefold()
            and sorted(word.casefold()) == reference_list]
