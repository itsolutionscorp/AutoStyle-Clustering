import re
def word_count(string):
    my_dict={}
    pattern = re.compile("\s+")
    words=[word.strip() for word in pattern.split(string)]
    for word in words:
        if my_dict.has_key(word):
            my_dict[word]+=1
        else:
            my_dict[word]=1
    return my_dict
