def word_count(text):
    """Take in a string and return a dictonary of each word with the count."""

    count_dict = {}
    for word in text.split():
        if word in count_dict:
            # Have existing word so increment it
            count_dict[word] += 1
        else:
            # First occurance of the word
            count_dict[word] = 1
            
    return count_dict
