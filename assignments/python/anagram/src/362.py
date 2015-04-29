def detect_anagrams(word,word_list):
    ans_list =[]
    for w in word_list:
        w_temp=list(w.lower())
        word_temp=list(word.lower())
        if (len(w_temp)!=len(word_temp))or word.lower()==w.lower():   
            continue
        for n in word.lower():
            if not(len(w_temp)==0 and len(word_temp)==0):
                if n in w_temp:
                    w_temp.remove(n)
                    word_temp.remove(n)
                else:
                    break
        if (len(w_temp)==0 and len(word_temp)==0):
            ans_list.append(w)
    return ans_list
                
        
            
                
            
        
