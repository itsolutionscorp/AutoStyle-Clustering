def num_common_letters(goal_word, guess):
    counter = 0
    xlst = []
    for y in  get_list(goal_word):
        for x in get_list(guess):
            if y == x and x not in xlst:
                xlst += [x]
                counter += 1
    return counter
