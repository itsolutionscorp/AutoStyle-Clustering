def word_count(sentence):

    # strip out new line commands
    words = sentence.replace('\n', ' ')

    # define two lists, to hold the start and end indices of words in the sentence
    starts = []
    ends = []

    # iterate through each letter of words, appending indices to starts and ends as appropriate
    for x in range(len(words)):

        if words[x:x+1] == '\n':
            continue

        if words[x-1:x] == '\n':
            continue

        if x == 0 and words[x] != " ":
            # checks if x is the first letter of words - if it isn't a space, it's the start of a word
            starts.append(x)

        if x == len(words) - 1 and words[x] != " ":
            # checks if x is the last letter of words - if it isn't a space, it's the end of a word
            ends.append(x)

        if x > 0: #checking characters in the middle of words
            if words[x] != " " and words[x-1] == " ":
                # looks for a non-space character preceded by a space - indicates the start of a word
                starts.append(x)

        if x < len(words) - 1: #checking characters in the middle of words
            if words[x] != " " and words[x+1] == " ":
                # looks for a non-space character followed by a space - indicates the end of a word
                ends.append(x)

    #print 'starts: ' + str(starts)
    #print 'ends: ' + str(ends)

    # define an empy list to hold all words in words
    wordlist = []

    for y in range(len(starts)):
        # using lists of start and end indices, slice words into individual words
        wordlist.append(words[starts[y]:ends[y]+1])

    #print 'wordlist: ' + str(wordlist)

    # define dictionary to hold words
    dictionary = {}

    # iterate through wordlist and add to dictionary
    for w in wordlist:
        if w in dictionary:
            # check if word already in dictionary and iterate key value
            dictionary[w] += 1
        else:
            dictionary[w] = 1

    #print 'dictionary: ' + str(dictionary)
    return dictionary
