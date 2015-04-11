import re
def word_count(string):
    #make the string lowercase
    lower = string.lower()
    #matches all words using characters a-z and 0-9
    regex = re.compile("[a-z0-9]+")
    #find all matches in the lower case string
    words = regex.findall(lower)
    dict = {}
    for word in words:
        #if the word is in the dict add one to its counter
        #otherwise add it to the dict with count = 1
        if word in dict:
            dict[word] += 1
        else:
            dict[word] = 1
    return dict
