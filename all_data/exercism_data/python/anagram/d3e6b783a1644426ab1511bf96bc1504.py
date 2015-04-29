def detect_anagrams(word, potential_anagrams):
    word = word.lower()
    
    word_letters = { 
        letter : word.count(letter) for letter in word 
    }
    
    anagrams = []
    for potential_anagram in potential_anagrams:
        potential_anagram_lower = potential_anagram.lower()
        
        anagram_letters = { 
            letter : potential_anagram_lower.count(letter) for letter in potential_anagram_lower
        }

        if anagram_letters == word_letters and word.lower() != potential_anagram_lower:
            anagrams.append(potential_anagram)
    
    return anagrams
