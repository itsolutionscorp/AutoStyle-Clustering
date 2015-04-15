def detect_anagrams(ana, phrase):
    output = []
    anasort = sorted(ana.lower())
    for words in phrase:
        if len(words) == len(ana) and ana.lower() != words.lower():
            wordssort = sorted(words.lower())
            print words, wordssort
            if anasort == wordssort:
                print 'yes', words
                output.append(words)
    return output
