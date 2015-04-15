#
# Skeleton file for the Python "Bob" exercise.
#
def hey(statement):
    if statement.strip():
        if statement.isupper():
            return "Whoa, chill out!"
        elif statement.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
    else: 
        return "Fine. Be that way!"
