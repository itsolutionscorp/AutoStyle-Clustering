# File: wordcount.py
# Author: Theo Love
# Date: 201-09-23
# Description: A solution to the leap year problem.

def word_count(arg):
    # caste arg as a lowercase string and split up the words
    sentence = str(arg).lower().split()
    
    # setup the dictionary object to be returned
    word_list = {}
    
    # Iterate through the strings separated by spaces.
    # Add the word to the list if it isn't there with a count of 1.
    # Otherwise increment the count.
    for word in sentence:
        word = word.strip( '!@#$%^&*,:;<>?' )
        if word == '':
            pass
        elif word not in word_list: 
            word_list[word] = 1
        else:
            word_list[word] += 1
            
    return word_list
