#Written for Exercism python exercise (3)

def word_count(input):
    #Dictionary to hold words and their number of occurences
    freq = {}
    
    #using the split() method to create a list of individual words
    list = input.split()
    
    #creating a new list of the unique words from original
    unique_list = []
    for word in list:
        if word not in unique_list:
            unique_list.append(word)

    #adding key-value pairs by counting occurance of words.
    for word in unique_list:
        freq[word] = list.count(word)
    
    return freq
