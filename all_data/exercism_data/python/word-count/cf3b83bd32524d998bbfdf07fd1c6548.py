def word_count(sentence):
    wordlist = sentence.split()
    worddict ={}
    for word in wordlist:
        if not word in worddict:
            worddict[word] = 1;
        else:
            worddict[word] = worddict.get(word) + 1;

    return worddict;
