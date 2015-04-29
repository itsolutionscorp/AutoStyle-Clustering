#
#  exercism Bob exercise. By Rahul Rajeev<hello@rahulrajeev.co>
#
def hey(what):
    cleaned_input = what.strip()

    if cleaned_input.isupper():
        return "Whoa, chill out!"
    if cleaned_input.endswith('?'):
        return "Sure."
    if len(cleaned_input) == 0:
        return "Fine. Be that way!"

    return "Whatever."
