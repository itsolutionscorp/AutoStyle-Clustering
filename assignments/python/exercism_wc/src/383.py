def word_count(phrase):
    wordlist = phrase.split()
    counts = [wordlist.count(word) for word in wordlist]
    wordcounts = dict(zip(wordlist, counts))
    return wordcounts
