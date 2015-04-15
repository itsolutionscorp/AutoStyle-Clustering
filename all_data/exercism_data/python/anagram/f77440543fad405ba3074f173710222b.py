def detect_anagrams(word, pos_words):
    word_sort = sorted(list(word.lower()))
    ana_words = []
    for pos in pos_words:
        pos_sort = sorted(list(pos.lower()))
        if pos_sort == word_sort and word.lower() != pos.lower():
            ana_words.append(pos)
    return ana_words
