def word_count(phrase):
    ans = {}
    for each in phrase.split():
        if each.strip() in ans: ans[each.strip()]+=1
        else: ans[each.strip()] = 1
    return ans
