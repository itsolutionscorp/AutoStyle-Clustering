def num_common_letters(goal_word, guess):
    guess_word = get_list(guess)
    goal = get_list(goal_word)
    repeats = []
    similarity_score = 0
    i = 0
    while i < len(guess_word): 
        if guess_word[i] in goal and guess_word[i] not in repeats:
            similarity_score += 1
            repeats += guess_word[i]
        i += 1
    return similarity_score 
