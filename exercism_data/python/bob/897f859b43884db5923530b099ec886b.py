#!/usr/bin/python

"""
Bob is a lackadaisical teenager. In conversation, his responses are very
limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
"""

__version__ = "1.0"
__revision__ = "$Revision: $"
__all__ = ['__version__', '__revision__', 'hey']

def hey(statement):
    "This is apparently how one is to address Bob."

    # Remove any whitespace.
    statement = statement.strip()

    # Bob's default response to anything
    response = "Whatever."

    # If you shout, Bob will tell you to chill out.
    if statement.isupper():
        response = "Whoa, chill out!"

    # If you don't actually ask anything, he gets moody.
    elif not statement:
        response = "Fine. Be that way!"

    # If you ask a question he'll say 'Sure.'
    elif statement[-1] == '?':
        response = "Sure."

    return response

if __name__ == "__main__":

    # An example exchange with this eloquent man-child.

    STATEMENTS = [
            "I've got ice cream.",
            "I SAID, I'VE GOT ICE CREAM.",
            "Do you want an ice cream?",
            "",
            "What?",
            "    \t"
            ]

    for what_i_said in STATEMENTS:
        print "Me:  ", what_i_said
        print "Bob: ", hey(what_i_said)

