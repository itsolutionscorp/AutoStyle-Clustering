def word_count(sentence):
    """Count the occurrences of each unique word in a sentence.
    Parameters:
        sentence -- the string to count
    Returns a dictionary of words (key) and their counts (value)
    """
    count = {}
    for word in sentence.split():
        try:
            count[word] += 1
        except KeyError:
            count[word] = 1
    return count
if __name__ == '__main__':
    sentence = raw_input('What shall we count? ')
    print word_count(sentence)
