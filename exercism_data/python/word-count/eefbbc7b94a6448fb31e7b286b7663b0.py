def word_count(in_string):
    """
    Input:  string
    Output: A dictionary whose keys are each different word in input string 
            and whose values are the number of time each word appears in the 
            input string
    """
    words = in_string.split()

    return {word:words.count(word) for word in words} 
