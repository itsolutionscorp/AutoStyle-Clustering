def hey(speech):

    if speech.isupper():
        return 'Whoa, chill out!'

    if speech.endswith("?"):
        return 'Sure.'

    if speech.isspace() or not speech:
        return 'Fine. Be that way!'

    return 'Whatever.'
