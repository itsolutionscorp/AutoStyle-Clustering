import string
def word_count(in_str):
    exclude = set('.,')
    in_str = ' '.join(in_str.split())
    in_str = ''.join(ch for ch in in_str if ch not in exclude)
    word_list = in_str.rsplit(' ')
    outstr = {}
    wordlist_set = set(word_list)
    outstr = {word: word_list.count(word) for word in wordlist_set}
    return outstr
