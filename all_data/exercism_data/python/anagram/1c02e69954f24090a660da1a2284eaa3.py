def detect_anagrams(original_word, possible_anagrams):
    original_word_count = letter_count(original_word)
    list_of_anagrams = []

    for word in possible_anagrams:
        if original_word_count == letter_count(word) \
                and not word.lower() == original_word.lower():
            list_of_anagrams.append(word)

    return list_of_anagrams


def letter_count(word):
    letter_count = {}

    # Convert word to lowercase for case insensitivity
    word = word.lower()

    for char in word:
        if char not in letter_count:
            letter_count[char] = 1
        else:
            letter_count[char] += 1

    return letter_count
