def word_count(sentence):
    for char in sentence:
        if not char.isalnum():
            sentence = sentence.replace(char, ' ')
    split_sentence = sentence.split()
    word_dict = {}
    for word in split_sentence:
        if word.lower() in word_dict:
            word_dict[word.lower()] += 1
        else:
            word_dict[word.lower()] = 1
    return word_dict
