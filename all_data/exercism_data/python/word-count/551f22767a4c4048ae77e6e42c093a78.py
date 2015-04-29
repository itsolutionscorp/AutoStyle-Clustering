def word_count(phrase):

    words = phrase.split()

    occurences = {}

    for word in words:
        if word in occurences:
            occurences[word] = occurences[word] + 1
        else:
            occurences[word] = 1

    return occurences
