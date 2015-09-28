def num_common_letters(goal_word, guess):
    steps = 0
    a = get_list(goal_word)
    b = get_list(guess)
    for element in a:
         if element in b:
            steps += 1
    return steps
