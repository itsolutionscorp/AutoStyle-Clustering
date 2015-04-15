def word_count(phrase):
    word_count = {}
    if not phrase:
        return word_count

    word_list = phrase.split()
    #unique_words = set(word_list)
    # iterating only through unique word list
    for word in set(word_list):
        word_count[word] = word_list.count(word)

    return word_count
