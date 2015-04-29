#
# Skeleton file for the Python "Bob" exercise.
#
def hey(statement):
    if statement.isupper():
        return "Whoa, chill out!"
    if statement.endswith('?'):
        return "Sure."
    if not statement.strip():
        return "Fine. Be that way!"
    else: 
        return "Whatever."
