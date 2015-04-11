import re


def hey(what):
    phrase = what.strip()
    if phrase:
        all_letters = re.findall(r'[^\W\d_]', phrase, re.UNICODE)
        if all_letters and\
                ''.join(all_letters) == ''.join(all_letters).upper():
            return u'Whoa, chill out!'
        elif re.match(r'^.*\?$', phrase):
            return u'Sure.'
        else:
            return u'Whatever.'
    else:
        return u'Fine. Be that way!'
