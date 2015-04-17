def word_count(phrase):
    phrase = phrase.strip()
    phrase_words = phrase.split()
    word_count_dict = {}
    for word in phrase_words:
        try:
            word_count_dict[word] += 1
        except KeyError:
            word_count_dict[word] = 1
    return word_count_dict
