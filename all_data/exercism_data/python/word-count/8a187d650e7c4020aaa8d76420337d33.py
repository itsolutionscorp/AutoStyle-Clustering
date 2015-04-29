def word_count(phrase):
    dict1 = {}
    for val in phrase.split():
        if val not in dict1:
            dict1[val] = 1
        else :
            dict1[val] += 1
    
    return dict1
