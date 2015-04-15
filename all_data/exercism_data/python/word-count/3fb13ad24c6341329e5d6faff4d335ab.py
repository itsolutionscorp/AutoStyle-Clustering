def word_count(phrase):
    # Split the phrase into a list of words.
    wordlist = phrase.split()
    # Get the counts for each word in 'wordlist'
    counts = [wordlist.count(word) for word in wordlist]

    # Map each word with its count into a dictionary.
    wordcounts = dict(zip(wordlist, counts))

    return wordcounts
