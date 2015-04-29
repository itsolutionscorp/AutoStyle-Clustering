def detect_anagrams(word, candidate_list):
    
    #Normalizing, measuring, and sorting the input word
    word_length = len(word)
    word_lowercase = word.lower()
    word_sorted = sorted(word_lowercase)

    #Creates list of anagrams, checking candidate word against criteria
    anagrams = [candidate for candidate in candidate_list
                if len(candidate) == word_length
                and not candidate.lower() == word_lowercase
                and sorted(candidate.lower()) == word_sorted]
    
    return anagrams
