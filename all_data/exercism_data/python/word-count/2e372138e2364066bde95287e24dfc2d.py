def word_count(input):
    split_input = input.split()
    wordlist = {}
    for word in split_input:
        if word not in wordlist:
            wordlist[word] = 0
        wordlist[word] += 1
    return wordlist
