def word_count(sentence):
    wordlist = sentence.split()  # creates list by using split method
    wordcounter = {}  # creates empty dictionary which will hold the answers
    for element in wordlist:
        if element in wordcounter:
            wordcounter[element] += 1
        else:
            wordcounter[element] = 1
    return wordcounter
