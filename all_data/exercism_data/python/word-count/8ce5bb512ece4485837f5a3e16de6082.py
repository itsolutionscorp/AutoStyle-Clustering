def word_count(sentence):
    occurences = {}
    words = cleansentence(sentence).split(' ')

    for word in words:
        if word in occurences:
            occurences[word]+=1

        else:
            occurences[word]=1

    return occurences


def cleansentence(sentence):
    return ' '.join(sentence.split())
