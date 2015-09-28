def num_common_letters(goal_word, guess):
    used_letters = []
    count = 0
    goal_list = get_list(goal_word)
    for i in range(0, len(goal_word)):
        for j in range(0, len(guess)):
            if guess[j] == goal_list[i] and used_letters.count(guess[j]) == 0:
                count += 1
                used_letters.append(guess[j])
    return count
                
