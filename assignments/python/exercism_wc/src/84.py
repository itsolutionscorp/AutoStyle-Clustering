import re
def word_count(text):
    results = {}
    text = re.findall(r"[^\s]+",text.strip())
    print text
    for word in text:
        if word in results:
            results[word] += 1
        else:
            results[word] = 1
    return results
