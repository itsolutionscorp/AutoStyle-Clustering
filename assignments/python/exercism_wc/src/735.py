import string
def word_count(phrase):
    punct = set(string.punctuation)
    no_punct =''
    for char in phrase:
       if char not in punct:
           no_punct += char
    dict = {}
    for word in no_punct.split():
       str = word.lower()
       if str not in dict:
           dict[str] = 1
       else:
           dict[str] +=1
    return dict
