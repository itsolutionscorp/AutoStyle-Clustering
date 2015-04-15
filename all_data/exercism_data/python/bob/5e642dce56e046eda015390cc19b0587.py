#
# Skeleton file for the Python "Bob" exercise.
#


def is_digit_sentence(a):
    if a.isdigit() or not a.isalpha():
        return True

    return False


def is_shout(sentence):
    # If there are lower case words then its not shout
    if any(map(str.islower, sentence)):
        return False
    else:
        if all(map(is_digit_sentence, sentence)):
            return False
        else:
            return True


def is_silence(what):
    if len(what) == 0 or len(what.strip()) == 0:
        return True
    return False


def hey(what):
    if is_silence(what):
        return 'Fine. Be that way!'

    if is_shout(what):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
    return
