#
# Skeleton file for the Python "Bob" exercise.

#bob is a lackadaisical teenager. In conversation, his responses are very limited.

#Bob answers 'Sure.' if you ask him a question.

#He answers 'Whoa, chill out!' if you yell at him.

#He says 'Fine. Be that way!' if you address him without actually saying
#anything.

#He answers 'Whatever.' to anything else. """


def hey(what):
    what = what.strip()
    if what is what.strip() == '':
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what.endswith("?"):
        return 'Sure.'
    else:
        return 'Whatever.'

hey("hello")
