def word_count(prompt):
    wordcount = dict()
    for word in prompt.split():
        wordcount[word] = wordcount.get(word, 0) + 1
    return wordcount
