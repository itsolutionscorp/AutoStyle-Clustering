import operator

def detect_anagrams(testword, wordlist):
    
    anagrams = []    
    testdict = {x: testword.lower().count(x) for x in testword.lower()}
    
    for word in wordlist:
        worddict = {x: word.lower().count(x) for x in word.lower()}
        if testdict == worddict and testword.lower() != word.lower():
            anagrams.append(word)
    
    return anagrams

# Decided to create a dict of every word containing letter: count (similar
# to the word_count exercise) aftermy cunning plan with sets turned out
# to be hopelessly flawed. I am sure there are much better ways to do this.
# Please feel free to educate me!
