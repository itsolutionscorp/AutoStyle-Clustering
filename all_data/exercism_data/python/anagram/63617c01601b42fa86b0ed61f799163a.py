def detect_anagrams(key, words):

    def check_word(possible_match):
        characters = []
        for character in possible_match:
            characters += character.lower()
        return sorted(characters) == sorted(key_letters)

    key_letters = []
    for letter in key:
        key_letters += letter.lower()

    matches = []
    for word in words:
        if len(word) != len(key) or word.lower() == key.lower():
            continue
        elif check_word(word):
            matches.append(word)

    return matches
