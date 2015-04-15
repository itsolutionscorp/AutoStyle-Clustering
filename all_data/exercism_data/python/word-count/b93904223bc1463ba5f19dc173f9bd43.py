'''
Word counting

'Words' include numbers but not symbols
'''
from collections import defaultdict


def word_count(text):
    '''
    Return a dict of words and their counts
    '''
    
    # All entries start at a count of 0
    counts = defaultdict(lambda: 0)

    # Case insensitive
    text = text.lower()

    tokens = text.split()
    for token in tokens:
        token = remove_symbols(token)
        if token:
            counts[token] += 1

    return counts


def remove_symbols(token):
    '''
    Remove symbols from the supplied token
    '''
    clean_token = ''.join([char for char in token if char.isalnum()])
    return clean_token
            
