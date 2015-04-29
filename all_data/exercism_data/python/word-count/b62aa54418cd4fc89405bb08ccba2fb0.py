#
#

def word_count(phrase):
    #insert function here
    wordcounts = {}
    for word in phrase.split():
        if word not in wordcounts:
            wordcounts[word] = 1
        else: wordcounts[word] += 1

    return wordcounts
