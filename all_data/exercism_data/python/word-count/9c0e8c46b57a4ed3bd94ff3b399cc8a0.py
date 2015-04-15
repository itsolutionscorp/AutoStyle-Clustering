from collections import Counter

def word_count(phrase):
    ans = Counter()
    for each in phrase.split(): ans[each.strip()] += 1
    return ans
