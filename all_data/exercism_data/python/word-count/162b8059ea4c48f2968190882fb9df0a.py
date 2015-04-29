# word-count exercism

def word_count(s):
    words = s.split() # make list of words
    count = {} # dictionary to store count for each word
    for word in words:
        if word not in count: # if we have a new word we will add it to our dictionary
            count[word] = 1
        else: # if we have seen this word before we will increase the count by one
            count[word] += 1
            
    return count
