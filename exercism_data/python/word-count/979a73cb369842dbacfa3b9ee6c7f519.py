def word_count(phrase):
    words = phrase.replace("\n", " ").split(" ")
    word_count_dict = {}
    for word in words:
        if not word:
            continue
        if word not in word_count_dict.keys():
            word_count_dict.update({word: 1})
        else:
            word_count_dict[word] += 1
    return word_count_dict
