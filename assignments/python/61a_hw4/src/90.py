def num_common_letters(goal_word, guess):
    no_repeat = []
    common_letter = [letter for letter in get_list(guess) if letter in get_list(goal_word)]
    for letter in common_letter:
        if letter not in no_repeat:
            no_repeat += [letter]
    return len(no_repeat)
