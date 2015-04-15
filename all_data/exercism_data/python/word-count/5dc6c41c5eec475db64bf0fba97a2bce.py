def word_count(phrase):
    
    phrasedict = {}
    for word in phrase.split():
        if word not in phrasedict:
            phrasedict[word] = phrase.split().count(word)
    return phrasedict
