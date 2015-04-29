def Allergies(score):

    alist = []

    if score >= 128:
        score-=128
        alist.append('cats')
    if score >= 64:
        score-=64
        alist.append('pollen')
    if score >= 32:
        score-=32
        alist.append('chocolate')
    if score >= 16:
        score-=16
        alist.append('tomatoes')
    if score >= 8:
        score-=8
        alist.append('strawberries')
    if score >= 4:
        score-=4
        alist.append('shellfish')
    if score >= 2:
        score-=2
        alist.append('peanuts')
    if score >= 1:
        score-=1
        alist.append('eggs')



    return alist
