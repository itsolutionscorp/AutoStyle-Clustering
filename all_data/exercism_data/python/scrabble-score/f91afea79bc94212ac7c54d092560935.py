values = {}
for c in 'aeioulnrst':
    values[c]=1
for c in 'dg':
    values[c]=2
for c in 'bcmp':
    values[c]=3
for c in 'fhvwy':
    values[c]=4
values['k']=5
for c in 'jx':
    values[c]=6
for c in 'qz':
    values[c]=10

def score(word):
    total = 0
    for x in word:
        if x.lower() in values:
            total+= values[x.lower()]
    return total
