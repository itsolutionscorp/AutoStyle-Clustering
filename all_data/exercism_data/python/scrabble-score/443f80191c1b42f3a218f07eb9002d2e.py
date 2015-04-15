scrabble_values = {
    1 : ['a','e','i','o','u','l','n','r','s','t'],
    2 : ['d','g'],
    3 : ['b','c','m','p'] ,
    4 : ['f','h','v','w','y'],
    5 : ['k'],
    8 : ['j','x'],
   10 : ['q','z'] }

def score(word):
    return sum(map(get_scrabble_value, word.lower()))

def get_scrabble_value(char):
    for k in scrabble_values.keys():
        if char in scrabble_values[k]: return k
    return 0
