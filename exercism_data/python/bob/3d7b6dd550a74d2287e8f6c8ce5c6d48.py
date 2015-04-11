#
# Skeleton file for the Python "Bob" exercise.
#
def hey(statement):
    statement = statement.strip()
    if statement.isspace() or len(statement) == 0:
        return 'Fine. Be that way!'
    elif statement != statement.lower() and statement.upper() == statement:
        return 'Whoa, chill out!'
    elif statement.endswith('?'):
            return 'Sure.'
    else:
        return 'Whatever.'
