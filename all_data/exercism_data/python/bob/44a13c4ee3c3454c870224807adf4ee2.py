def hey(question):
    question = question.rstrip()

    if not question or question.isspace():
        return 'Fine. Be that way!'
    elif question.isupper():
        return 'Woah, chill out!'
    elif question.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
