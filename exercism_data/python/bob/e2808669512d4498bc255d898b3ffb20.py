import re

def hey(text):
    words = text.split()
    wordcount = len(words)
    yell = 0
    question = None

    if wordcount >= 1:
        
        for idx,w in enumerate(words):
            c = re.subn(r'[^a-zA-Z0-9äöüÄÖÜ]','',w)
            if c[1] == len(w):
                wordcount -= 1
            if re.match(r'[A-ZÄÖÜ]{'+str(len(c[0]))+r'}',c[0]) != None:
                yell += 1
                if idx == wordcount-1 and re.search(r'!+',w):
                    return ('Woah, chill out!')
                
        if yell == wordcount:
            return('Woah, chill out!')
        
        question = re.search(r'\?',words[-1])
        if question != None:
            return ('Sure.')

        return('Whatever.')

    else:
        return('Fine. Be that way!')
        
