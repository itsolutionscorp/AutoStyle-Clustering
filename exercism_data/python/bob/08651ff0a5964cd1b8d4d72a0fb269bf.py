#
# Skeleton file for the Python "Bob" exercise.
#


def is_digit_sentence(a):
    # Checks if given input is either digit or non alphabetic
    # characters and returns true.
    if a.isdigit() or not a.isalpha():
        return True

    return False


def is_shout(sentence):
    # If there are lower case words then its not shout
    if any(map(str.islower, sentence)):
        return False
    else:
        # If this sentence is only made of digits and punctuations
        # then this is not a shout
        if all(map(is_digit_sentence, sentence)):
            return False
        else:
            return True


def is_silence(what):
    # Checks length of given input stripped of whitespace is zero
    if len(what) == 0 or len(what.strip()) == 0:
        return True
    return False


def hey(what):
    # Check input is silence or prolonged silence
    if is_silence(what):
        return 'Fine. Be that way!'

    # Check if input is shout
    if is_shout(what):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        # Or if sentence ends with a ?
        return 'Sure.'
    else:
        # Otherwise
        return 'Whatever.'
