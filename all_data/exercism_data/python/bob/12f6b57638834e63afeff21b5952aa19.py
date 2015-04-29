#
# Skeleton file for the Python "Bob" exercise.
#
#Bob is a lackadaisical teenager. In conversation, his responses are very limited.
#
#Bob answers 'Sure.' if you ask him a question.
#
#He answers 'Whoa, chill out!' if you yell at him.
#
#He says 'Fine. Be that way!' if you address him without actually saying
#anything.
#
#He answers 'Whatever.' to anything else.

def hey(what):
    what = what.strip()
    end = len(what)
    if end == 0:
        return "Fine. Be that way!"
    if what[end - 1] == "?" and end != 0 and not what.isupper():
        return "Sure."
    elif what.isupper():
        return "Whoa, chill out!"
    else:
        return "Whatever."
