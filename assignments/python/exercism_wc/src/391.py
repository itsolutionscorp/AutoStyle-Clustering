def word_count(phrase):
    wordcounts = {}
    for word in phrase.split():
        wordcounts[word] = wordcounts.get(word,0)+1
    return wordcounts
