def word_count(arg):
    wordcount = {}
    words = arg.split()
    for word in words:
        if word in wordcount:
            wordcount.update({word: wordcount[word] + 1})
        else:
            wordcount.update({word: 1})
    return wordcount
