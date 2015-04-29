#
# Skeleton file for the Python "Bob" exercise.
#
def hey(statement):
    if statement.isspace() or len(statement) == 0:
        return 'Fine. Be that way!'
    elif statement != statement.lower() and statement.upper() == statement:
        return 'Whoa, chill out!'
    elif '?' in statement and '.' in statement:
        if statement[len(statement)-1] == '?':
            return 'Sure.'
        elif statement[len(statement)-1] == '.':
            return 'Whatever.'
    elif '?' in statement:
            return 'Sure.'
    else:
        return 'Whatever.'
