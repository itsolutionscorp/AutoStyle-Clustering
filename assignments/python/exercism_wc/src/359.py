import re
def word_count(foo):
    items = re.findall(r'(?:^|\b|\s)(\S+)(?:$|\b|\s)',foo)
    counts = dict()
    for i in items:
        counts[i] = counts.get(i,0) + 1
    return counts
if __name__ == "__main__":
    print word_count("'car : carpet as java : javascript!!&@$%^&'")
