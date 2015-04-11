

def word_count (sentance):
    words = []
    count = []
    word_ct = {}
    if sentance[0] > ' ':
        start_word = 0                        #
    else:
        for x in range(1,len(sentance)):
            if sentance[x] > ' ':
                start_word = x
                break
    # the above block is unnecessary to pass the tests
    # it finds the first character in the string and starts
    # the first word with that character. it would be needed if
    # any string started with a space or new line or any of the sort        
    for x in range(start_word,len(sentance)):
        if x == len(sentance)-1:
            if not sentance[x] <= ' ':
                words.append(sentance[start_word:])
                count.append(1)                      
        if sentance[x] <= ' ':
            if start_word == x:
                start_word += 1
                continue
            stop_word = x
            words.append(sentance[start_word:stop_word])
            count.append(1)
            start_word = x+1
    # the above groups sequential characters that are separated by 
    # space or other not characters to form words 
    for x in range(len(words)-1):
        for y in range(x+1,len(words)):
            if words[x] == ' ':
                continue
            if words[x] == words[y]:
                count[x] += 1
                words[y] = ' ' 
    # the above looks for duplicate words, removing the duplicate
    # and advancing the counter of the original                 
    for x in range(len(words)):
        if words[x] == ' ':
            continue
        else:
            word_ct[words[x]] = count[x]
    # the above adds the words and their count to the dictionary 
    return word_ct
    
                
            
        
        
        
        
        
        
        
            
