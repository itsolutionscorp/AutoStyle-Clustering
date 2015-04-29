RESPONSE = {
    'question': u'Sure.',
    'yell': u'Woah, chill out!',
    'nothing': u'Fine. Be that way!',
    'general': u'Whatever.'
}


def hey(msg):
    if msg.strip() == '':
        return RESPONSE['nothing']
    elif msg.isupper():
        return RESPONSE['yell']
    elif msg[-1] == '?':
        return RESPONSE['question']
    else:
        return RESPONSE['general']
