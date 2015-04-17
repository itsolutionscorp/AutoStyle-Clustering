__author__ = 'sagos'
def word_count(words):
    wordlist = words.split()
    freqlist ={}
    for word in wordlist:
        freqlist[word] = wordlist.count(word)
    return freqlist
