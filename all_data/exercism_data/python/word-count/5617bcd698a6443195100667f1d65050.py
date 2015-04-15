# Word Count

def word_count(words):
    
    # list of words in string
    split_words = words.split()
    
    # list of the count of each word, but without the word
    count_list = [split_words.count(word) for word in split_words]
    
    # zip creates a tuple of the two list's items
    # a dict is returned with no duplicates automatically
    # dicts do not allow duplicates
    dict_return = dict(zip(split_words, count_list))
    return dict_return
    
