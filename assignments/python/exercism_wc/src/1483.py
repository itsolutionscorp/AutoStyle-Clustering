def word_count(sentence):
    lines = sentence.splitlines()
    wordCount = {}
    for eachLine in lines:
        words = eachLine.split(' ')
        for eachWord in words:
            if eachWord == '':
                continue
            count = wordCount.get(eachWord)
            if count is None:
                wordCount[eachWord] = 1
            else:
                wordCount[eachWord] = count + 1
    return wordCount
