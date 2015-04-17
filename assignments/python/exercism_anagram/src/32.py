def detect_anagrams(word, word_list):
    sorted_word = sorted(word.lower())
    return [word2 for word2 in word_list if sorted(word2.lower()) == sorted_word \
            and not word2.lower() == word.lower()]
