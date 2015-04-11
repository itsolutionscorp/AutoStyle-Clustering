#
# Solution file for word_count_test.py, exercism.io
#
# v1: Initial version, split to list and add to dict

def word_count(phrase):
    '''
    Count # of words in phrase, return dict of count
    '''
    # Setup our empty dict
    wc = {}
  
    # String whitespace form our phrase
    string = phrase.strip()

    # Split our string into a list (default: space)
    wl = string.split()

    # Loop through our list
    for item in wl:
        # Check for existing key in dict
        if wc.has_key(item):
            # Existing key, increment our value by 1
            wc[item] = wc[item] + 1
        else:
            # New key, set value to 1
            wc[item] = 1

    # Return dict
    return wc
