def word_count(sentence):
    '''Count distinct words in a sentence.
    Words are separated by whitespaces.
    Punctuation marks count as words.'''
    word_list = sentence.split()
    word_dict = {}
    for word in word_list:
        word_dict[word] = 1 + word_dict.get(word, 0)
    return word_dict
