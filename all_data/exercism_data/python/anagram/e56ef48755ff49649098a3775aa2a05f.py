def detect_anagrams(master_word, words):
    return [word for word in words
            if letter_count(word) == letter_count(master_word)
            if word.lower() != master_word.lower()]

def letter_count(word):
    letters = {}
    for letter in word.lower():
        letters[letter] = letters.get(letter, 0) + 1
    return letters
