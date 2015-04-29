def hey(sentence):
    if sentence and not sentence.isspace():
        if sentence.isupper():
            return 'Whoa, chill out!'
        elif sentence[len(sentence)-1] == "?":
            return 'Sure.'
        else:
            return 'Whatever.'
    else:
        return 'Fine. Be that way!'
