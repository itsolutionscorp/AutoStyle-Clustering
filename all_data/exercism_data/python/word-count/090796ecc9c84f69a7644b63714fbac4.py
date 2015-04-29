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
    #The following prints the number of times each word appears
    for word in fixed:
        d[word] = fixed.count(word)
    return d
