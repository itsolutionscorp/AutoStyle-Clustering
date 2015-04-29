def word_count(string):
    counter = dict()
    #loop through each phrase, split on whitespace
    for phrase in string.split():
        #check if the phrase is in our dictionary. If not, add it with value 1
        if phrase not in counter:
            counter[phrase] = 1
        #otherwise increment it's value by one
        else:
            counter[phrase] += 1
    return(counter)
