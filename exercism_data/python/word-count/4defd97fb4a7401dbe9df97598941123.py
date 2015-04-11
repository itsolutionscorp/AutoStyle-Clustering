def word_count(testStr):

    # Create a new Dictionary to hold all the words
    word_dict = {}

    for word in testStr.split(): # iterate through words in string
        if word in word_dict.keys(): # check if word already in dict
           word_dict[word] += 1 # increase the counter in dict
        else:
           word_dict[word] = 1 # create new entry in dict and set counter to 1

    return word_dict
