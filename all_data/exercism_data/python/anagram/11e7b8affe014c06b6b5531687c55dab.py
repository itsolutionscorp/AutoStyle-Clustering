def detect_anagrams(word, possibles):
    return [w for w in possibles if is_anagram(word, w)]


def is_anagram(word, possible):
    word = word.upper()
    possible = possible.upper()

    if word == possible:
        return False

    if sorted(word) == sorted(possible):
        return True

    return False
