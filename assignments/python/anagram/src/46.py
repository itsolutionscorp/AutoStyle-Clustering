def detect_anagrams(word, maybes):
    word = word.lower()
    return [maybe for maybe in maybes if sorted(maybe.lower()) == sorted(word)
                                         and maybe.lower() != word]
