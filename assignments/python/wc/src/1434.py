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
    for x in range(len(words)-1):
        for y in range(x+1,len(words)):
            if words[x] == ' ':
                continue
            if words[x] == words[y]:
                count[x] += 1
                words[y] = ' ' 
    for x in range(len(words)):
        if words[x] == ' ':
            continue
        else:
            word_ct[words[x]] = count[x]
    return word_ct
