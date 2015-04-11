def score(word):
    s = 0
    word = word.lower()
    
    iterator = [(['a','e','i','o','u','l','n','r','s','t'],1),
                (['d','g'],2),
                (['b','c','m','p'],3),
                (['f','h','v','w','y'],4),
                (['k'],5),
                (['j','x'],8),
                (['q','z'],10)]
    
    for group in iterator:
        for letter in group[0]:
            if letter in word: s += word.count(letter) * group[1]
            
    return s
