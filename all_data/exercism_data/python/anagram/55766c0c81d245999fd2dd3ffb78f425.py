def is_anagram(word: str, other: str) -> bool:
    # if len(word) != len(other):
    #     return False

    word, other = word.lower(), other.lower()
    if word == other:
        return False

    return all(word.count(letter) == other.count(letter)
               for letter in set(word))


def detect_anagrams(word: str, wordlist: list) -> list:
    return [w for w in wordlist if is_anagram(word, w)]
