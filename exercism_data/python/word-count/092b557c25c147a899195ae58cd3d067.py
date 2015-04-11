import sys
def word_count(phrase):
    lexicon = {}
    wordList = phrase.split()
    for word in wordList:
        if word in lexicon:
            lexicon[word] = lexicon[word] + 1
        else:
            lexicon[word] = 1
    return lexicon

if __name__ == '__main__':  
    print word_count(sys.argv[1])
    #print sys.argv[1]
