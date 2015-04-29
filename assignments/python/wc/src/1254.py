import sys
def word_count(word):
    wordlist = word.split()
    worddict = {}
    for i in wordlist:
            if not(worddict.has_key(i)):
                worddict.update({i:1})
            else:
                value = worddict[i]
                worddict.update({i: (value +1)})
    return worddict
if __name__ == "__main__":
    print sys.argv[1]
    print len(sys.argv[1])
    print sys.argv[1].split(' ')
    print(word_count(sys.argv[1]))
