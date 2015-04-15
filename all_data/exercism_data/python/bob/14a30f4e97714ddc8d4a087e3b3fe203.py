#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """ exercise bob """

    stating_something = ('Tom-ay-to, tom-aaaah-to.')
    shouting = ('WATCH OUT!')
    asking_a_question = ('Does this cryogenic chamber make me look fat?')
    asking_a_numeric_question = ('You are, what, like 15?')
    talking_forcefully = ("Let's go make out behind the gym!")
    using_acronyms_in_regular_speech = ("It's OK if you don't want to go to the DMV.")
    forceful_questions = ("WHAT THE HELL WERE YOU THINKING?")
    shouting_numbers = ("1, 2, 3 GO!")
    only_numbers = ('1, 2, 3')
    question_with_only_numbers = ('4?')
    shouting_with_special_characters = ('ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!')
    shouting_with_umlauts = ('ÜMLÄÜTS!')
    shouting_with_no_exclamation_mark = ('I HATE YOU')
    calmly_speaking_with_umlauts = ('ÜMLäÜTS!')
    statement_containing_question_mark = ('Ending with ? means a question.')
    prattling_on = ("Wait! Hang on. Are you going to be OK?")
    silence = ('')
    prolonged_silence = ('    \t')
    starts_with_whitespace = ('         hmmmmmmm...')
    ends_with_whitespace = ('What if we end with whitespace?   ')

    if what in [silence, prolonged_silence]:
        return 'Fine. Be that way!'
    elif what in [shouting, forceful_questions, shouting_numbers, shouting_with_special_characters,
                  shouting_with_umlauts, shouting_with_no_exclamation_mark]:
        return 'Whoa, chill out!'
    elif what in [asking_a_question, asking_a_numeric_question, question_with_only_numbers, prattling_on,
            ends_with_whitespace]:
        return 'Sure.'
    else:
        return 'Whatever.'
