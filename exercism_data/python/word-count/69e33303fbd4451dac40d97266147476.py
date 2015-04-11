def word_count(sentence):
    wordlist = sentence.split()  # creates list by using split method
    wordcounter = {}  # creates empty dictionary which will hold the answers
    for element in wordlist:
        # this for loop checks if the word exists in dictionary
        # if yes it increments its value by 1
        # if not it creates new dictionary entry with value of 1
        if element in wordcounter:
            wordcounter[element] += 1
        else:
            wordcounter[element] = 1
    return wordcounter
