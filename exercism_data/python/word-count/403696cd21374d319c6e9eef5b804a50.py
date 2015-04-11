import collections

def word_count(sentence):
    '''Count distinct words in a sentence.

    Words are separated by whitespaces.
    Punctuation marks count as words.'''

    word_list = sentence.split()
    word_dict = dict(collections.Counter(word_list))

    return word_dict
