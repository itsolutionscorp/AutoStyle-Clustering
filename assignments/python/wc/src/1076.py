import re
def word_count(string):
    string = re.sub('[)(*&^%$#@!:;,]','', string);
    dictionary = {};
    splitwords = string.lower().split();
    for word in splitwords:
        if word in dictionary:
            dictionary[word] = dictionary[word] + 1;
        else:
            dictionary[word] = 1;
    return dictionary;
