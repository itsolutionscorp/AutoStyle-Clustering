def detect_anagrams(original, candidates):
    anagrams = []
    for word in candidates:
        temp_word = word.lower()
        for letter in original.lower():
            if letter not in temp_word: 
                break
            temp_word = temp_word.replace(letter,'', 1)  # Removes matched letters to avoid double matching
        else:
            if len(original) == len(word) and original.lower() != word.lower():
                anagrams.append(word) 
    return anagrams
