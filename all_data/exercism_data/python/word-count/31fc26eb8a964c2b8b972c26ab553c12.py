def word_count(string):

    #string = string.lower()            #seemd more logical to me, changed so the test file likes it
    stringInPieces = string.split()
    worddict = {}

    for word in stringInPieces:

        if word in worddict:
            worddict[word] += 1

        else:
            worddict[word] = 1

        return worddict

    

    #for key, value in worddict.items():    #thought i need to print it..
        #print key +": "+str(value)
            

        

        
