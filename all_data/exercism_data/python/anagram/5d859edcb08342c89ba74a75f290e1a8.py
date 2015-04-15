def check_word(possible_match, key_letters):
        characters = []
        for character in possible_match:
            characters += character.lower()
        return sorted(characters) == sorted(key_letters)


def detect_anagrams(key, words):
    key_letters = []
    for letter in key:
        key_letters += letter.lower()

    matches = []
    for word in words:
        if len(word) != len(key) or word.lower() == key.lower():
            continue
        elif check_word(word, key_letters):
            matches.append(word)

    return matches
