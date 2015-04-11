def word_count(phrase):
    phrase = phrase.split()
    phrasedict = {}
    for word in phrase:
        if word not in phrasedict:
            phrasedict[word] = phrase.count(word)
    return phrasedict
