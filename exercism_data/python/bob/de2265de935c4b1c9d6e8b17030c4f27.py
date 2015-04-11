
def hey(userInput):
    BOT_RESPONSE = {
        '?': 'Sure.',
        'loud': 'Whoa, chill out!',
        'saidNothing': 'Fine. Be that way!',
        'default': 'Whatever.'
    };

    response = BOT_RESPONSE['default'];

    # No need to save any spaces for any of this
    userInput = userInput.strip();

    # List comprehension to save only the letters into onlyLetters
    onlyLetters = ''.join(filter(str.isalpha, userInput));

    # Store the last character of input off in a check to make
    # life a little easier later
    LAST_CHAR = userInput[-1:];

    # No real input given.
    if userInput == '':
        response = BOT_RESPONSE['saidNothing'];

    # Forceful talking
    elif len(onlyLetters.strip()) > 0 and \
    onlyLetters.upper() == onlyLetters:
        response = BOT_RESPONSE['loud'];

    # Check for questions or other defaults based on punctuation
    elif LAST_CHAR in BOT_RESPONSE.keys():
            response = BOT_RESPONSE[LAST_CHAR];

    return response;

if __name__ == '__main__':
    print(hey(input('Say something to bob: ')));
