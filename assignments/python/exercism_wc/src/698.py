def word_count(sentence):
    letters = [chr(ord("a") + a) for a in range(26)] + [str(i) for i in range(10)] + [' ']
    sentence = filter(lambda x: x in letters, sentence.lower())
    wordlist = filter(lambda y: len(y) > 0, sentence.split(" "))
    wordcount = dict([(w, wordlist.count(w)) for w in list(set(wordlist))])
    return wordcount
