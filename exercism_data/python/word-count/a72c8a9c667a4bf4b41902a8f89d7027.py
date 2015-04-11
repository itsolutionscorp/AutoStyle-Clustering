def word_count(sentence):

    collection = {}
    for a in sentence.split():
        if collection.has_key(a):
            collection[a] += 1
        else:
            collection[a] = 1
    
    return collection
