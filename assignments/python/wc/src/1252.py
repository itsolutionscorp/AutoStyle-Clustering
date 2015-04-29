def word_count(string):
    string = string.strip()
    wordcount = {};
    for word in string.split():
        if word in wordcount:
            wordcount[word] = wordcount[word] + 1
        else :
            wordcount[word] = 1
    return wordcount
