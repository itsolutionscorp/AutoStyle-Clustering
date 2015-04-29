#
# Skeleton file for the Python "Bob" exercise.
#
#import sys
#what = sys.argv[1]
#print what


def hey(what):
        length = len(what)

        # Test for Numbers
        if what.isdigit():
            return "Whatever."
        elif(what == ''):
            return "Fine. Be that way!"
        elif (what.isspace()):
             return "Fine. Be that way!"
        elif(what.isupper()):
            return 'Whoa, chill out!'
        elif (what[(len(what) -1)] == "?"):
            return "Sure."
        else:
              return 'Whatever.'






#print(hey(what))
