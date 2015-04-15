def detect_anagrams(word, guesses):
    word = word.lower()
    sorted_word = sorted(word)

    anagrams = []

    for guess in guesses:
        g = guess.lower()
        if g != word and sorted(g) == sorted_word:
            anagrams.append(guess)

    return anagrams
