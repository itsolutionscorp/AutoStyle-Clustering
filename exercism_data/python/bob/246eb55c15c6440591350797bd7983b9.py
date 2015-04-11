#kling 11/13/2014
#Exercism Exercise 1 - Bob

def hey(sentence):
    #remove leading and trailing whitespace
    sentence = sentence.strip()

    if sentence.isupper():
        reply = 'Whoa, chill out!'
    elif sentence == "":
        reply = 'Fine. Be that way!'
    elif sentence[len(sentence)-1] == '?':
        reply = 'Sure.'
    else:
        reply = 'Whatever.'

    return reply

