def hey(question):
    question = question.rstrip()

    if question == '':
        return 'Fine. Be that way!'
    elif question.isupper():
        return 'Woah, chill out!'
    elif question[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
