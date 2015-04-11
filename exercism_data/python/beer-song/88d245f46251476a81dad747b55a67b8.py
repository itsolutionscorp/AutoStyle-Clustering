FIRST = ' bottles of beer on the wall, '
SECOND = ' bottles of beer.\n'
THIRD = 'Take one down and pass it around, '
FOURTH = 'Go to the store and buy some more, '
def song(big,small=0):
    str = ''
    for i in range(big,small-1,-1):
        str += verse(i)+'\n'
    return str



def verse(amount):
    
    if amount==1:
        amounted = 'no more'
        return str(amount) + FIRST.replace('bottles','bottle') + str(amount) + SECOND.replace('bottles','bottle') + THIRD.replace('one','it') + amounted + FIRST.replace(', ','.\n')
    elif amount==0:
        amount = 'no more'
        amounted = '99'
        return (amount.replace('n','N') + FIRST + str(amount) + SECOND + FOURTH + amounted + FIRST.replace(', ','.\n'))
    elif amount==2:
        amount = str(amount)
        amounted = '1'
        return (amount + FIRST + amount + SECOND + THIRD + amounted + FIRST.replace(', ','.\n').replace('bottles','bottle'))
    else:
        amounted = str(int(amount)-1)
    amount = str(amount)
    return (amount + FIRST + amount + SECOND + THIRD + amounted + FIRST.replace(', ','.\n'))
