def distance(word1,word2):
    count = 0
    for i,c in enumerate(word1):
        if( word2[i] == c) :
            pass
        else:
            count = count +1
    return count
