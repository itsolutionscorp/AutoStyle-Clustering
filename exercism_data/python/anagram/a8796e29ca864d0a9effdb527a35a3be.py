def detect_anagrams(givenWord, wordList):
    anagrams=[]

    # Convert "givenWord" to lowercase.
    # Make a list of the characters in the "givenWord."
    # Sort the list alphabetically.
    sortedLetters=sorted(list(givenWord.lower()))

    # Do the same for each "word" in the "wordList"
    for word in wordList:

        # If the two lists are equal, but are not identical words, add "word" to "anagrams."
        if sortedLetters==sorted(list(word.lower())) and (givenWord.lower() !=word.lower()):
            anagrams.append(word)

    return anagrams

