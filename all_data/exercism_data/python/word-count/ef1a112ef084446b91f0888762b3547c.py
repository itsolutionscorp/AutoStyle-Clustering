
def word_count(phrase):

    result = {}
    words = phrase.split()

##    for word in words:
##        if( word in result):
##            result[word] = result[word] + 1;
##        else:
##            result[word] = 1


##    return result
    return {word : words.count(word) for word in words} 

