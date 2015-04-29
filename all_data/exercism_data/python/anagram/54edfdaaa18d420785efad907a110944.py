def detect_anagrams(givenWord, possibleAnagrams):
    occurances = {}
    anagrams = []
    givenWord = givenWord.lower()
    # Populate dict with the number of occurances of letters in givenWord.
    for ch in givenWord:
        if not ch in occurances:
            occurances[ch] = givenWord.count(ch)
    for possibleWord in possibleAnagrams:
        word = possibleWord.lower()
        if word != givenWord and len(word) == len(givenWord):
            # Fill a temporary dict with letter occurances in possibleWord.
            lettersInWord ={}
            for letter in word:
                if not letter in lettersInWord:
                    lettersInWord[letter] = word.count(letter)
            if lettersInWord == occurances:
                anagrams.append(possibleWord)
    return anagrams
