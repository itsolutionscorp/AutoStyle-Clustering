#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if len(what) == 0 or what.strip() == '':
        return 'Fine. Be that way!'
    else:
        stripped_what = what.strip()
        last_char = stripped_what[-1]

        if last_char == "?":
            """
            This fails on the test_forceful_questions test case.
            I'm not certain why. It seems like this if should be
            executed and the return should end the function before
            it ever matches the first elif.

            If I run the function on my own and pass the string
            'WHAT THE HELL WERE YOU THINKING?' to hey(), last_char is '?'

            Pretty confused.
            """
            return 'Sure.'
        elif what.upper() == what and last_char != '?':
            return 'Whoa, chill out!'
        elif len(what) <= 0:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
    return

hey(raw_input())
