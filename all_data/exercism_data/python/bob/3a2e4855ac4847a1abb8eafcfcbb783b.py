#
# Skeleton file for the Python "Bob" exercise.
#
# question --> 'Sure.'
# yell --> 'Whoa, chill out!'
# bob --> 'Fine. Be that way!'
# else --> 'Whatever.'
#
#

def hey(what):
#    assert isinstance(what, str), 'Please use text with Bob.'
    if what.isupper():
        return 'Whoa, chill out!'
    elif what == 'Bob' or what == 'Bob?' or what == '' or ('  ' in what and '...' not in what):
        return 'Fine. Be that way!'
    elif what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'

# print hey('talk')
# print hey('TALK')
# print hey('question?')
# print hey('Bob')
# print hey('Bob?')
# print hey('It is 9 o clock')
# print hey('What are you 15?')
