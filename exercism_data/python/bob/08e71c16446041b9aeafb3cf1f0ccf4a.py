#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what != '':
        last_char = what[-1]
        if last_char == '?':
            return 'Sure.\n'
        elif last_char == '!':
            return 'Whoa, chill out!\n'
    else: 
        return  'Fine. Be that way!\n'
    return 'Whatever\n'
    
r = True  
print ("""
    This is Bob. You can talk with him.
    Try asking him some questions, or saying him 
    somethig, also try to yell at him.
    To exit just type in exit or quit.
""") 

while r:
    question = input('Speak with Bob\n')
    if question == 'exit' or question == 'quit':
        r = False
    else:
        print(hey(question))

        
