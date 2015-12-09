def num_common_letters(goal_word, guess):
    used_letters = []    
    def common_helper(goal_word, guess):
        if len(guess) == 0:
            return 0
        if guess[0] in used_letters:
            return common_helper(goal_word, guess[1:])
        elif guess[0] in goal_word:
            used_letters.append(guess[0])
            return 1 + common_helper(goal_word, guess[1:])
        else:
            return common_helper(goal_word, guess[1:])
    return common_helper(goal_word, guess)