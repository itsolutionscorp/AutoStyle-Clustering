def to_rna(s):
    
    news = ''

    for c in s:
        
        if c == 'G':
            news += 'C'
        elif c == 'C':
            news += 'G'
        elif c == 'T':
            news += 'A'
        elif c == 'A':
            news += 'U'

    return news
