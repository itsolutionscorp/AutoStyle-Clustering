def score(word, bonus=""):
    """bonus is a string that will be the same length of word with the matching
    letter multiplier; characters after the length of word in bonus indicate
    word score multipliers"""
    word = word.lower().strip()
    #pad bonus if it is the default string to pass the test suite
    if bonus == "":
        for i in range(len(word)):
            bonus += "1"
    d = {"a": 1, "b": 3, "c": 3, "d": 2, "e": 1,"f": 4, "g": 2, "h": 4, "i": 1,
         "j": 8,"k": 5, "l": 1, "m": 3, "n": 1, "o": 1,"p": 3, "q": 10, "r": 1,
         "s": 1, "t": 1,"u": 1, "v": 4, "w": 4, "x": 8, "y": 4,"z": 10}
    tmp = zip(word, bonus)
    tmpsum = sum(( (d[v] * int(b)) for v,b in tmp))
    #apply word multipliers
    for multiplier in bonus[len(word): len(bonus)+1]:
        tmpsum = tmpsum * int(multiplier)
    return tmpsum
