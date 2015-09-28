def num_common_letters(goal_word, guess):
    goal_letters = get_list(goal_word)
    guess_letters = get_list(guess)
    count = 0
    i = 0
    used_letters = []
    while i < min(len(goal_word), len(guess)):
        if guess_letters[i] in goal_letters and guess_letters[i] not in used_letters:
            count += 1
            used_letters += guess_letters[i]
            i += 1
        else:
            i += 1
    return count
