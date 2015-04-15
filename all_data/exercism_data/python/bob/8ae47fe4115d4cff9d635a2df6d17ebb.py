def hey(question):
    if len(question.strip()) == 0:
        return "Fine. Be that way!"
    
    hasWord = False
    isShout = True
    for w in question:
        if w.isalpha():
            hasWord = True
        for c in w:
            if c.isalpha() and not c.isupper():
                isShout = False
    if not hasWord:
        isShout = False

    if isShout:
        return "Whoa, chill out!"
    
    if question[-1] == '?':
        return "Sure."
    return "Whatever."
        
