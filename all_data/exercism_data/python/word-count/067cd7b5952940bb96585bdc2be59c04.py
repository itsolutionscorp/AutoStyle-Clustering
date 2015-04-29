def word_count(in_string):
    """
    Input:  string
    Output: A dictionary whose keys are each different word in input string 
            and whose values are the number of time each word appears in the 
            input string
    """
    words = in_string.split()
    count_words = dict()
    for word in words:
        if word in count_words:
            count_words[word] += 1
        else:
            count_words[word] = 1

    return count_words 
