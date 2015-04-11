from collections import Counter

def word_count(input_):
    inputlist = input_.rsplit()
    wordcountdict = Counter(inputlist)

    return wordcountdict

# Initially tried this, don't know why it did not work?

#def word_count(input_):
#    inputlist = input_.rsplit()
#    wordcountdict = {}
#    for word in inputlist:
#        wordcountdict[word] = wordcountdict.get(word, 1) + 1
#
#    return wordcountdict
