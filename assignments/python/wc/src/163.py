def word_count(phrase):
    "given 'string' count the occurence of each word"
    phrase = phrase.replace('\t\n\r', ' ')
    word_dict = {}
    for word in phrase.split():
        if word not in word_dict:
            word_dict[word] = 1
        else:
            word_dict[word] = word_dict[word] + 1
    return word_dict
