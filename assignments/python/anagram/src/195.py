def detect_anagrams(word, list_to_test):
    true_anagrams=[]
    
    #check all words in list_to_test
    while len(list_to_test) > 0:
        word_to_test = list_to_test.pop(0)
        
        if len(word_to_test) == len(word):
            #breakup words into list of chars
            word_breakup = list(word) 
            test_breakup = list(word_to_test)
            
            #make lower case
            word_breakup = [x.lower() for x in word_breakup]
            test_breakup = [x.lower() for x in test_breakup]
                    
            #sort the strings and compare
            if word_breakup != test_breakup:
                word_breakup.sort()
                test_breakup.sort()
                if word_breakup == test_breakup:
                    true_anagrams.append(word_to_test)
            
    return true_anagrams 
