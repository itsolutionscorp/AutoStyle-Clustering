#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    '''
    Bob is a lackadaisical teenager. In conversation, his responses are very limited.

    Bob answers 'Sure.' if you ask him a question.

    He answers 'Whoa, chill out!' if you yell at him.

    He says 'Fine. Be that way!' if you address him without actually saying
    anything.

    He answers 'Whatever.' to anything else.
    '''
    # yelling (i.e. all caps) ascii characters
    if what == what.upper() and what.upper() != what.lower():
        return 'Whoa, chill out!'

    # questions
    if what.strip().endswith('?'):
        return 'Sure.'

    # nothing
    if not what.strip():
        return 'Fine. Be that way!'

    # no match
    return 'Whatever.'
