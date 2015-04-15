# Takes a sentence, splits it to words and returns the count of how many times each word appears.

def word_count(phrase):
    # split to list of separate words:
    words = phrase.split()
    
    # set up dict:
    wordcount = {}
        
    # count the words:
    for w in words:
        if not w in wordcount:
            # add new word to dict, with count = 1
            wordcount[w] = 1
        else:
            # increment existing count:
            wordcount[w] += 1

    # output:
    return wordcount
