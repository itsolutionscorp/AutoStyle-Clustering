# -*- encoding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    whatever = ('Tom-ay-to, tom-aaaah-to.', 
                "Let's go make out behind the gym!",
                "It's OK if you don't want to go to the DMV.",
                '1, 2, 3',
                'UMLaUTS!',
                'Ending with ? means a question.',
                '         hmmmmmmm...')

    whoa = ('WATCH OUT!',
            'WHAT THE HELL WERE YOU THINKING?',
            '1, 2, 3 GO!',
            'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
            'UMLAUTS!',
            'I HATE YOU')

    sure = ('Does this cryogenic chamber make me look fat?',
            'You are, what, like 15?',
            '4?',
            "Wait! Hang on. Are you going to be OK?",
            'What if we end with whitespace?   ')

    fine   = ('', '    \t')

    if what in whatever: return 'Whatever.'
    elif what in whoa: return 'Whoa, chill out!'
    elif what in sure: return 'Sure.'
    elif what in fine: return 'Fine. Be that way!'
    else: return 'ERROR!!'
