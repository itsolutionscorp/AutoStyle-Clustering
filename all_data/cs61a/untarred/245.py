def num_common_letters(goal_word, guess):
  
    a, b = get_list(goal_word), get_list(guess)
    total = 0 
    x = 0 
    a_len, b_len = len(a), len(b)
    while x < a_len:
        y = 0 
        while y < b_len: 
            if a[x] == b[y]:
                total += 1
                y = b_len + 1
            else: 
                y += 1
        x += 1
    return total 
