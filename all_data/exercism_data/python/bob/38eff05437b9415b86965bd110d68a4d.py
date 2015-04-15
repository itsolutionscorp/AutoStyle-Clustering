def hey(sentence):
    # Strip the sentence
    sentence = sentence.strip()

    # If you don't say anything to Bob, Bob says 'Fine. Be that way!'
    if len(sentence) == 0:
        response = 'Fine. Be that way!'
    # If you yell at Bob, Bob says 'Whoa, chill out!'
    elif sentence.isupper():
        response = 'Whoa, chill out!'
    # If you ask Bob a question, Bob says 'Sure.'
    elif sentence[-1] == '?':
        response = 'Sure.'
    # Anything else you say to Bob, Bob says 'Whatever.'
    else:
        response = 'Whatever.'

    return response
