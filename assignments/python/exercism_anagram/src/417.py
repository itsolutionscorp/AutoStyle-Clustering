def detect_anagrams(word, potential_anagrams):
    sorted_word = sorted(word.lower())
    
    anagrams = [potential_anagram for potential_anagram in potential_anagrams 
                    if sorted(potential_anagram.lower()) == sorted_word and 
                    potential_anagram.lower() != word.lower() 
                ]
    return anagrams
