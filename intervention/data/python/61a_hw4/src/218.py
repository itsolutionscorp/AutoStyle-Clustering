def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    already_seen = []
    i = 0
    while i <= len(goal_list) - 1: 
        if guess_list[i] in goal_list and guess_list[i] not in already_seen:
             already_seen += [guess_list[i]]
        i += 1
    return len(already_seen)
