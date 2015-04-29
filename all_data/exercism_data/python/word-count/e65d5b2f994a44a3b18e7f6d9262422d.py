author = 'Greg'

def word_count(sent):
    '''Counts words, defined as sets of characters separated by spaces and
    excluding punctuation.'''
    sent = ''.join([x if x.isalnum() else ' ' for x in sent])
    # create sequence of alphanumeric characters only with same variable name as
    # input, removing punctuation
    sent = sent.lower().split() 
    counts = {}  # dict to store words, number of appearances as key/value 
    for word in sent:
        if word in counts: 
            counts[word] += 1 
        else: 
            counts[word] = 1 
    return counts 
