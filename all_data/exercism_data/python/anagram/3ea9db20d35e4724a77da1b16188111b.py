def detect_anagrams(anagram, phrase):
    def is_anagram(anagram, test):
        if anagram.lower()==test.lower() or len(anagram) != len(test):
            return False
        anagram=anagram.lower()
        test=test.lower()
        indt=-1
        #For every letter
        while indt <len(test)-1:
            count=-1
            indt+=1
            #For every letter in the word
            while count<len(test)-1:
                #Increase the count
                count+=1
                #If the letters are the same
                if anagram[count]==test[indt]:
                    #Remove the letter from each word and set count back to -1
                    test=test[:indt]+test[indt+1:]
                    anagram=anagram[:count]+anagram[count+1:]
                    count=-1
                    indt=-1
                    break
        return test==""
            

    output=[]
    for word in phrase:
        if is_anagram(anagram,word):
            output.append(word)
    return output
