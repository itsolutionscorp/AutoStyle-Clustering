def num_common_letters(goal_word, guess):
    guess_without_repeats = []
    for elem in get_list(guess):
        if elem not in guess_without_repeats:
            guess_without_repeats = guess_without_repeats + [elem]
    common_letters = [] + [x for x in guess_without_repeats if x in get_list(goal_word)]
    return len(common_letters)
            
            
