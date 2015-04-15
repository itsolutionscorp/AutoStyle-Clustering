def hey(question):
    ret = None
    if question.isupper():
        ret = u'Whoa, chill out!'
    elif question.endswith('?'):
        ret = u'Sure.'
    elif question.isspace() or not question:
        ret = u'Fine. Be that way!'
    else:
        ret = u'Whatever.'
        
    return ret
