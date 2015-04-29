def detect_anagrams(word, word_list):
    return [w for w in word_list if sorted(word.lower()) == sorted(w.lower()) and w.lower() != word.lower()]
