import re
import string


shout_answer = 'Whoa, chill out!'
question_answer = 'Sure.'
nothing_answer = 'Fine. Be that way!'
default_answer = 'Whatever.'

def hey(sentence):
    if is_saying_nothing(sentence):
        return nothing_answer

    if is_shouting(sentence):
        return shout_answer

    if is_asking_question(sentence):
        return question_answer

    return default_answer

def is_saying_nothing(sentence):
    sentence = filter_from_sentence(sentence, 'w')
    return not sentence

def is_shouting(sentence):
    sentence = filter_from_sentence(sentence, 'wdp')
    return sentence and sentence.upper() == sentence

def is_asking_question(sentence):
    return '?' == sentence[-1]

def filter_from_sentence(sentence, filter_chars=''):
    """Returns sentence stripped of type of strings.

    The types of strings that can be stripped are
    whitespaces, punctuation, digits, letters.

    Args:
        sentence: The sentence  you want it stripped.
        filter_chars: String of characters. Possible values:
         - 'w' ignore whitespace
         - 'p' ignore punctuation
         - 'd' ignore digits
         - 'l' ignore letters

    Returns:
        A string without characters of specified type in filter_chars.
    """
    # Remove characters 'c' from 's'.
    remove_chars = lambda s, c: re.sub('[' + c  + ']', '', s)

    if 'w' in filter_chars:
        sentence = remove_chars(sentence, string.whitespace)
    if 'p' in filter_chars:
        sentence = remove_chars(sentence, string.punctuation)
    if 'd' in filter_chars:
        sentence = remove_chars(sentence, string.digits)
    if 'l' in filter_chars:
        sentence = remove_chars(sentence, string.letters)
    return sentence
