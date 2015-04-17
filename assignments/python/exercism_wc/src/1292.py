def word_count(string):
    """splits a string into a series of phrases and counts their occurences"""
    counter = dict()
    for phrase in string.split():
        if phrase not in counter:
            counter[phrase] = 1
        else:
            counter[phrase] += 1
    return(counter)
