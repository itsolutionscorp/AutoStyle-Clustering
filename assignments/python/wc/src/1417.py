def word_count(instr):
    '''
    counts the number of times each word occurs in a given string
    '''    
    words = instr.split()
    result = dict() #create a dictionary
    for word in words:
        if word in result: # if we already have this word in our dictionary
            result[word] += 1 # increment the count 
        else:
            result[word] = 1 # else create a new entry in our dictionary with count 1
    return result
