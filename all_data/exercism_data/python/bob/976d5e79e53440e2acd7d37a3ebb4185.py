from __future__ import unicode_literals

def hey(text):

    if bool(text) is False or text.isspace() is True:
        return 'Fine. Be that way!'

    elif text == text.upper() and (text.lower() != text.upper()):
        return 'Whoa, chill out!'

    elif text[-1] == '?':
        return 'Sure.'

    elif bool(text) == True:
        return 'Whatever.'




if __name__ == '__main__':
    while True:
        text = raw_input('Talk to Bob.\n\n>>')
        reply = str(hey(text))
        print reply
