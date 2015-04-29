##
def encode(phrase):
    
    phrase = phrase.lower()
    phrase_list = list(phrase.replace(' ', ''))
         
    Plain = list('abcdefghijklmnopqrstuvwxyz')
    Cipher = list('zyxwvutsrqponmlkjihgfedcba')
    
    P_C = zip(Plain, Cipher)
    
    translate = []
    for a in phrase_list:
        try:
            int(a)
            translate.append(a)
        except ValueError:
            for b, c in P_C:
                if a == b:
                    translate.append(c)
                else:
                    pass

    t_string = ''.join(translate)
    output = []
    
    n=0
    while True:
        if n < len(t_string)-5:
            piece = t_string[n:n+5]
            output.append(piece+' ')
            n+=5
        elif n >= len(t_string)-5:
            piece = t_string[n:len(t_string)]
            output.append(piece)
            break
        else:
            pass
            
    return ''.join(output)
                
##            
def decode(phrase):

    phrase = phrase.lower()
    phrase_list = list(phrase.replace(' ', ''))
         
    Plain = list('abcdefghijklmnopqrstuvwxyz')
    Cipher = list('zyxwvutsrqponmlkjihgfedcba')
    
    P_C = zip(Plain, Cipher)
    
    translate = []
    for a in phrase_list:
        try:
            int(a)
            translate.append(a)
        except ValueError:
            for b, c in P_C:
                if a == c:
                    translate.append(b)
                else:
                    pass

    return ''.join(translate)
