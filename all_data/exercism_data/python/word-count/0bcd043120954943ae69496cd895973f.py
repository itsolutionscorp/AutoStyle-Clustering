def word_count(word):

    word_list = word.split()
    dic ={}
    for n in word_list:     
        if n in dic:
            dic[n]+=1
        else:
            dic[n]=1
    return dic
        
        

        

        
        
    
