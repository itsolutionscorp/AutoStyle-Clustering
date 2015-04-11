#
# "Bob" exercise.
# TR
#
def hey(what):
    if shouting(what):
        return 'Whoa, chill out!'
    if asking_a_question(what):
        return 'Sure.'
    if silence(what):
        return 'Fine. Be that way!'
    return 'Whatever.'

# Shouting is:
# * More uppercase than lowercase characters
# * Ending in an '!'
def shouting(what):
    uppers = lowers = 0
    for character in what:
        uppers = uppers + bool(character.isupper())
        lowers = lowers + bool(character.islower())
    return uppers > 0 and lowers == 0

# A question ends with a '?'
def asking_a_question(what):
    return what.strip()[-1:] == '?'

# Silence is an empty string
def silence(what):
    return what.strip() == ''
