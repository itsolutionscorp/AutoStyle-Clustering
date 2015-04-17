def word_count(string):
    counter = dict()
    for phrase in string.split():
        if phrase not in counter:
            counter[phrase] = 1
        else:
            counter[phrase] += 1
    return(counter)
