'''
Given a phrase, count the occurrences of each word in that phrase.

Return a dictionary of the count
'''

def word_count(phrase):
    # split phrase into words
    words = phrase.split()
    # create empty dict
    words_dict = {}
    # iterate through each word
    for word in words:
        # add new words to dict
        if not words_dict.get(word):
            words_dict.update({word:1})
        # otherwise increment count
        else:
            n = words_dict.get(word) + 1
            words_dict[word] = n
    # return dictionary
    return words_dict

