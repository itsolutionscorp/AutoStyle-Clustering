import string

def hey(dialogue):
    answer = 'Whatever.'
    if dialogue.isspace() or len(dialogue)==0:
        answer = 'Fine. Be that way!'
    else:
        if dialogue[-1]== "?":
            answer = 'Sure.'
        if dialogue.isupper():
            answer = 'Whoa, chill out!'
    return answer
