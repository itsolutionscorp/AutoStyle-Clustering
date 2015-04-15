# Word count
# made by ez

def word_count(target):
    words = target.split()
    result = {}
    
    for word in words:
        if word in result:
            result[word] += 1
        else:
            result[word] = 1
    return result
