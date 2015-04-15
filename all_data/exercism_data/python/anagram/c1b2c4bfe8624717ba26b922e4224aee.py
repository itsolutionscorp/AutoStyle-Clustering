def detect_anagrams(word,words):
    letters={}
    anagrams=[]
    word=word.lower()

    #go through words
    for w in words:
        letters.clear()
        w=w.lower()
        found=True
        
        print w
        #cant be the same word
        if (w == word):
            break
       
        #dictonary of word
        for c in word:
            if c in letters:
                letters[c]+=1
            else:
                letters[c]=1
                
        #go through characters in w
        for c in w:
            #if c exist on the dictionary
            if c in letters:
                #check if it still has letters
                if(letters[c]>0):
                    letters[c]-=1
                else:
                    found=False
                    break
            else:
                found=False
                break
            

            if(found==False):
                break 
        
        for c in letters:
            if(letters[c]>0):
                found=False
                break
            
        if(found):
            anagrams.append(w)
               
    return anagrams


print detect_anagrams(
                'allergy',
                'gallery ballerina regally clergy largely leading'.split()
            )
    
    
