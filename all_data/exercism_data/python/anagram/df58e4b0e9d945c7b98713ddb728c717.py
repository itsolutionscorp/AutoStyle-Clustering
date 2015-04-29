def detect_anagrams(reference, word_list):

    reference = reference.casefold()
    ref_list = sorted(reference)
    detect_anagram = lambda w1 : w1 != ref and sorted(w1) == ref_list
    
    return [word
            for word
            in word_list
            if detect_anagram(word.casefold())]
