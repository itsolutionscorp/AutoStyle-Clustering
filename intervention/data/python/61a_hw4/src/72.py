def num_common_letters(goal_word, guess):
    def count(gw, g, n):
        if len(g) == 0: 
            return count(gw[1:], guess, n)
        if len(gw) == 0:
            return n
        if gw[0] == g[0]:
            return count(gw[1:], guess, n + 1)
        else: return count(gw, g[1:], n)
    return count(get_list(goal_word), get_list(guess), 0)
