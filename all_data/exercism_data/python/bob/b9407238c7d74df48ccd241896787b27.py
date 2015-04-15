def hey(text):
    """bob.hey is a fuction to speak to bob, be careful what are you going to said him"""
    words = text.split()
    is_q = False
    if len(words) == 0:
        return 'Fine. Be that way!'
    else:
        if words[-1][-1] == '?':
            is_q = True
        stop = False
        have_text = False
        for i in range(len(words)):
            for j in range(len(words[i])):
                lettre = words[i][j]
                if lettre.isalpha():
                    have_text = True
                    if lettre.islower():
                        stop = True
                        break
            if stop:
                yell = False
                break
            elif len(words)-1 == i and have_text:
                yell = True
        if yell:
            return 'Woah, chill out!'
        elif is_q:
            return 'Sure.'
        else:
            return 'Whatever.'
