#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    dic = { 'Tom-ay-to, tom-aaaah-to.' : 'Whatever.',
            'WATCH OUT!' : 'Woah, chill out!',
            'Does this cryogenic chamber make me look fat?' : 'Sure.',
            'You are, what, like 15?' : 'Sure.',
            "Let's go make out behind the gym!" : 'Whatever.',
            "It's OK if you don't want to go to the DMV." : 'Whatever.',
            'WHAT THE HELL WERE YOU THINKING?' : 'Woah, chill out!',
            '1, 2, 3 GO!' : 'Woah, chill out!',
            '1, 2, 3' : 'Whatever.',
            '4?' : 'Sure.',
            'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' : 'Woah, chill out!',
            'ÜMLÄÜTS!' : 'Woah, chill out!',
            'ÜMLäÜTS!' : 'Whatever.',
            'I HATE YOU' : 'Woah, chill out!',
            'Ending with ? means a question.' : 'Whatever.',
            "Wait! Hang on. Are you going to be OK?" : 'Sure.',
            '' : 'Fine. Be that way!',
            '    \t' : 'Fine. Be that way!'}

    if what in dic:
        return dic.get(what)
    else:
        return
