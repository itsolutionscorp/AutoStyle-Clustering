def word_count(message):
    word_dict = {}
    messagelist = message.split()
    for word in messagelist:
       stripped = word.strip()
       word_dict.setdefault(stripped, 0)
       word_dict[stripped] += 1
    return word_dict
