def word_count(word):

    word_list = word.split()

    temp = word_list[:]
    
    done=[]

    dic ={}
    #print word_list 
    for n in word_list:
        #print dic, word_list
        
        x = temp.count(n)

        if x==0:
            continue
        
        #print x,n
        dic[n] = x

        for m in range(x):

            #print n

            temp.remove(n)

    #print word_list
    return dic

        
        

        

        
        
    
