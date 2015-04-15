def word_count(sentence):
    """Count the occurrences of each word in a given sentence.
    
    Args:
        sentence (str): A sentence.
         
    Returns:
        A dictionary containing each word found in a sentence as a key and 
        the number of occurrences as a value.
        
    Raises:
        None.
    """
    
    split = sentence.split()
    words = {}
    
    for s in set(split):
        words[s] = split.count(s)
        
    return words
