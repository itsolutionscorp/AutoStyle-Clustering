def hey(input):

    #is it empty or just whitespace?
    if (input.strip() == ''):
        return 'Fine. Be that way!'
    #are you yelling at him?
    if (input.isupper()):
        return 'Whoa, chill out!'
    #is it a question?
    if (input.endswith('?')):
        return 'Sure.'
    return 'Whatever.'
