def detect_anagrams(word, words):
    sorted_word = sorted(word.lower())
    return [new_word
            for new_word in words
            if (word != new_word.lower()) and (sorted_word == sorted(new_word.lower()))]
