def detect_anagrams(word, list_of_possibilities):
    if type(word)!=type("hello"):
        return "Word is invalid"
    word=word.lower()
    if type(list_of_possibilities)==type(["hello", "world", "python"]):
        pos=list_of_possibilities    #wow that was a long name
    else:
        return "You must input a valid list of possibilities"
    letters=[]
    posreturn=list_of_possibilities
    for posib in pos:
        print str(posreturn) + " POS return"
        print str(list_of_possibilities) + " List_of_possibilities"
        print str(pos) + " POS"  #HELP ME, Everytime I run this all three of these pos alternitives Change everytime WHYYYYY!!!!!
        for letter in word:
            letters.append(letter)
        removed=False
        posibl=posib.lower()
        print posib
        print posibl
        print"\n"
        if word==posibl:
            print removed
            if removed==False:
                posreturn.remove(posib)
                removed==True
        let=[]
        for letter in posibl:
            let.append(letter)
        for letter in let:
            print str(letters) + " " + letter
            try:
                letters.remove(letter)
            except:
                print str(reuseletters)
                print posib + " FINAL"
                print removed
                if removed==False:
                    posreturn.remove(posib)
                    removed==True
        if len(letters)!=0:
            print removed
            if removed==False:
                posreturn.remove(posib)
                removed==True
                
        
        
        
    return posreturn

    
    
        
