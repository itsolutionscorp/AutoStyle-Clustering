def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    for i in range(len(guess_list)):
        n = i-1
        while n >= 0:
            if guess_list[i] == guess_list[n]:
                guess_list[i] = ""
            n -= 1
    num_letters = 0 
    for i in range(len(guess_list)):
        for j in range(len(goal_list)):
            if guess_list[i] == goal_list[j]:
                num_letters += 1
    return num_letters
