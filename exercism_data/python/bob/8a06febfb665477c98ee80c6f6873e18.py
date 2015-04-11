answers = {
    'question': 'Sure.',
    'yelling': 'Whoa, chill out!',
    'empty': 'Fine. Be that way!',
    'else': 'Whatever.'
}


def hey(what):
    what = what.strip()
    if what.isupper():
        return answers['yelling']
    elif what.endswith('?'):
        return answers['question']
    elif not what:
        return answers['empty']

    return answers['else']
