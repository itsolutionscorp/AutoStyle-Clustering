def word_count(text):
''' input is first split at every whitespace
    result: list of single words
    dict comprehension turns key:value expressions into dictionary
    '''
    wordlist = text.split()
    return {word: wordlist.count(word) for word in wordlist}
