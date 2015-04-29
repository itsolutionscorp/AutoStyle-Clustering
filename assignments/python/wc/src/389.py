import string
def word_count(phrase):
    '''
    Given a phrase, count the occurrences of each word.
    '''
    word_count_dictonary = {}
    for word in phrase.split():
        word = word.strip(string.punctuation).lower()
        if word == '':
            continue
        if word not in word_count_dictonary:
            word_count_dictonary[word] = 1
        else:
            word_count_dictonary[word] = word_count_dictonary[word] + 1
    return word_count_dictonary
