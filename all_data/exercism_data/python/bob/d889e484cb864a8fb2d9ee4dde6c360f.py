def hey(in_str):
    if len(in_str)==0 or len(in_str.strip(" \t"))==0:
        ##String is empty or has only spaces and/or tabs
        return 'Fine. Be that way!'
    if in_str.isupper():
        ##String is all uppercase
        return 'Whoa, chill out!'
    if in_str[-1]=='?':
        ##String ends with a question mark
        return 'Sure.'
    return 'Whatever.'
