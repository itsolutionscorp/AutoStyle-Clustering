def num_common_letters(goal_word, guess):
    x = goal_word
    y = guess
    if get_string(x) == '' or get_string(y) == '':
        return 0
    new_string = ''
    previous = 0
    for letter1 in get_string(x):
        for letter2 in get_string(y):
            if letter1 == letter2:
                if previous == letter2:
                    new_string = new_string
                    previous = letter1
                else:
                    new_string = new_string + letter2
                    previous = letter2
    return len(new_string)
