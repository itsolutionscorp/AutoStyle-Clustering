def detect_anagrams(word, anagrams):
    matches = []
    word = word.lower()
    for anagram in anagrams:
        if anagram.lower() == word:
            continue
        temp_anagram = bytearray(anagram.lower())
        for letter in word:
            if letter in temp_anagram:
                temp_anagram.remove(letter)
            else:
                break
        if len(temp_anagram) is 0:
            matches.append(anagram)

    return matches
