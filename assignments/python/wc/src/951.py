import string
import re
def word_count(sent):
    sent = re.sub('[!@&%#$;,?:^+=]', '', sent)
    print(sent)
    sent = sent.strip().lower().split()
    print(sent)
    counts = {}
    for x in range(len(sent)):
        if sent[x] in counts:
            counts[sent[x]] += 1
        else:
            counts[sent[x]] = 1
    return counts
