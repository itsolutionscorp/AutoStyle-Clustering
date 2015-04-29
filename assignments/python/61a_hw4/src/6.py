def num_common_letters(goal_word, guess):
    list_goal_word = get_list(goal_word)
    list_guess = get_list(guess)
    string_goal_word = get_string(goal_word)
    string_guess = get_string(guess)
    total = 0
    for i in list_goal_word:
        for r in list_guess:
            if list_goal_word[i] == list_guess[r]:
                total += 1
            return total
    for i in string_goal_word:
        for r in string_guess:
            if string_goal_word[i] == string_guess[r]:
                total += 1
            return total
