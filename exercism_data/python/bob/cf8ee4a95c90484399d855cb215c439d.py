#
# Skeleton file for the Python "Bob" exercise.
#
statement = "         hmmmmmmm..."

class Bob(object):
    def hey(self, statement):
        if statement.isupper():
            return 'Whoa, chill out!'
        elif statement.endswith("?"):
            return 'Sure.'
        elif statement == '' or statement.isspace():
            return 'Fine. Be that way!'
        elif statement.endswith("?") and statement.upper == statement:
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'

bob = Bob()

print bob.hey(statement)
