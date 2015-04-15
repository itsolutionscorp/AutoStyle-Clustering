#
# Skeleton file for the Python "Bob" exercise.
#
#Bob is a lackadaisical teenager.
#In conversation, his responses are very limited.

#order of precedence based on bob_test.py, yelling>question
"""
need to come up with some way to pass the special test case:
def test_prolonged_silence(self):
        self.assertEqual(
            'Fine. Be that way!', bob.hey('    \t')
"""
def said_nothing(something):
    nothing_said=True
    for char in something:
        if (char!='' and char!=' ' and char!='\t' and char!='\n'):
            nothing_said=False
    return nothing_said
#
def hey(what):
    
    #He answers 'Whoa, chill out!' if you yell at him.
    if what.isupper():
        return 'Whoa, chill out!'

    #He says 'Fine. Be that way!' if you address him without actually saying
    #anything.
    if said_nothing(what):
        return 'Fine. Be that way!'

    #Bob answers 'Sure.' if you ask him a question.
    #probably better to check for empty string before checking for '?' to avoid
    #string index out of range errors, rearranged order of tests
    if what[-1]=='?':
        return 'Sure.'
    
    #He answers 'Whatever.' to anything else.
    return 'Whatever.'
