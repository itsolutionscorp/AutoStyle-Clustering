#
# Skeleton file for the Python "Bob" exercise.
#
def hey(word):
    if word.isupper():
        return "Whoa, chill out!"
    elif word.strip() == "":
        return 'Fine. Be that way!'
    elif word.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
