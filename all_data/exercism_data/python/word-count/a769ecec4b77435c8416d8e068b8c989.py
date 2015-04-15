def word_count(passage):
    '''
    Counts occurrences of each unique word in a phrase.
    1 input(string) --> 1 output (dictionary)
    '''
    words = passage.split()              #Splits string into list of words
    result = {}                          #Create empty dictionary
    for word in words:
        result[word] = words.count(word) #Enters word count into dictionary
    return result
