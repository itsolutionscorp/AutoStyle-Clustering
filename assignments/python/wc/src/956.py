def word_count(passage):
    '''
    Counts occurrences of each unique word in a phrase.
    1 input (string) --> 1 output (dictionary)
    '''
    words = passage.split()
    result = {}                          #Create empty dictionary
    for word in set(words):              #Iterate through unique set of words
        result[word] = words.count(word) #Enters word count into dictionary
    return result
