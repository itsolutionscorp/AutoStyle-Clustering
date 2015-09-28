def num_common_letters(goal_word, guess):
    counter = 0
    repeats = []
    for elem in get_list(goal_word):
        if elem in repeats:
            counter+=0
        elif elem in get_list(guess):
            counter+=1
    return counter
