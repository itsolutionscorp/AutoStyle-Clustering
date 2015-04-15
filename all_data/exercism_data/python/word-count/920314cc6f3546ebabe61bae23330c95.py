def word_count(phrase):
    all_words = phrase.strip().split()
    word_dict = {}
    for word in all_words:
        word_exists = word_dict.get(word)
        if word_exists:
            word_dict[word] += 1
        else:
            word_dict[word] = 1
    return word_dict
