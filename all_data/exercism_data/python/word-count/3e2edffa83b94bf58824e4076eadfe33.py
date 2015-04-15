#Word count

def word_count(sentence):
    #Below:  Takes away all the spaces, creates a single, indexable list
    new = sentence.split()
    #Below:  Creates my dictionary
    d_list = {}
    #Below:  Checks each word in new
    for word in new:
    #Below:  Counts each occurrence of each word in the list, then assigns it to the dictionary d
        d_list[word] = new.count(word)
    return d_list
