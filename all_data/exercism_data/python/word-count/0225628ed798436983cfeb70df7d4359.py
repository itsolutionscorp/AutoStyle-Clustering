#Word count

def word_count(sentence):
    #Below:  Takes away all the spaces, creates a single, indexable list
    fixed = sentence.split()
    compare_to = []
    #Below:  Creates removes all the doubles and creates a new list, so I can compare the two
    for word in fixed:
        if word not in compare_to:
            compare_to.append(word)
    #Below:  Creates my dictionary
    d = {}
    #Below:  Checks each word in fixed
    for word in fixed:
    #Below:  Counts each occurrence of each word in the list, then assigns it to the dictionary d
        d[word] = fixed.count(word)
    return d
