import re

def hey(w):
    # Yelling
    patterns = {
        'r"^[^a-z]+$"': 'Whoa, chill out!',
        'r"[a-z]+\?"': 'Sure.',
    }

    for pattern, message in patterns.iteritems():
        x = re.match(pattern, w)
        if x:
            print x.group()
        else:
            print 'no match'
        if x and x.group() == w:
            print 'MATCJED!'
            return message
        else:
            return 'Whatever.'


if __name__ == '__main__':
    hey('hello?')
