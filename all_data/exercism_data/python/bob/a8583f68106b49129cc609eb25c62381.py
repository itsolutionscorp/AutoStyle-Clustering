#
# Skeleton file for the Python "Bob" exercise.
#
import re
question = re.compile('\\S+\\s*\\?')
yelling = re.compile('\\S+\\s*\\!')
address = re.compile('\\bBob\\b')
def respond_to( something ) :
    if question.search( something ) : return 'Sure.'
    if yelling.search( something ) : return 'Whoa, chill out!'
    if address.search( something ) : return 'Fine. Be that way!'
    return 'Whatever.'

if __name__ == '__main__' :
    while True :
        something = input('... ')
        if something :
            print( respond_to( something ) )
        else: break
