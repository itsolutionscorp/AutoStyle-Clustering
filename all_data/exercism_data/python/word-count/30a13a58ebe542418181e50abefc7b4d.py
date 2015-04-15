#wordcount module

def word_count(sentence):
    '''
    count words in the sentence
    :param sentense: String
    :return: dictionary with word as key and count as value
    '''

    word_counts = {}

    for word in sentence.split():
        if word in word_counts:
            word_counts[word] +=1
        else:
            word_counts[word] = 1

    return word_counts
