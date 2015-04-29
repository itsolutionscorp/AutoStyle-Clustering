def word_count(phrase):
    phrase = phrase.replace("\n"," ")
    words = phrase.split(" ")
    word_dict = {}
    try:
        while 1:
            words.remove('')
    except ValueError:
        pass
    for word in words:
        if word_dict.has_key(word):
            word_dict[word] = word_dict[word] + 1
        else:
            word_dict[word] = 1
    return word_dict
