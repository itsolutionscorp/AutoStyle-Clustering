def detect_anagrams(bossword, list_of_words):
    lowerboss = bossword.lower()
    return [word for word in list_of_words if
            sorted(word.lower()) == sorted(lowerboss) and word.lower() != lowerboss]
