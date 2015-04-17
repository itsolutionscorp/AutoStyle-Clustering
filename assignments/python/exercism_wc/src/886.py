"""Word Count function: counts the number of words in an input phrase
Input: a string with words separated by blank space
Returns: a dictionary with words as the key and number of occurences as the values"""
def word_count(input_string):
    input_words = input_string.split()
    wordcount_dict = dict()
    for word in input_words:
        if word in wordcount_dict:
            wordcount_dict[word] += 1
        else:
            wordcount_dict[word] = 1
    return wordcount_dict
