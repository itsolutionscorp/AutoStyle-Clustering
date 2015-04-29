def detect_anagrams(word, possibles):
    return [w for w in possibles if is_anagram(word, w)]


def is_anagram(word, possible):
    word = word.upper()
    possible = possible.upper()
    if word == possible:
        return False
    if len(word) != len(possible):
        return False

    possible_list = list(possible)
    for letter in word:
        try:
            i = possible_list.index(letter)
        except ValueError:
            return False
        possible_list.pop(i)

    if len(possible_list):
        return False

    return True
