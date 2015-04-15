def verse_line_1(beer_num):
    bottles = i_to_a_beer(beer_num)
    return bottles + ' on the wall, ' + bottles.lower() + '.\n'

def verse_line_2(beer_num):
    
    it = 'one'
    if beer_num == 0:
        it = 'it'
        
    if beer_num < 0:
        part1 =  "Go to the store and buy some more, "
        beer_num = 99
    else:
        part1 = "Take " + it + " down and pass it around, "
        
    bottles = i_to_a_beer(beer_num)
        
    return  part1 + bottles.lower() + ' on the wall.\n'
    
    
def i_to_a_beer(beer_num):
    if beer_num == 0:
        beer_num = 'No more'
    if beer_num == 1:
        return str(beer_num) + ' bottle of beer'
    else:
        return str(beer_num) + ' bottles of beer'

def verse(beer_num):
    return verse_line_1(beer_num) + verse_line_2(beer_num - 1)
    
def song(high, low=0):
    return ''.join([verse(beer_num) + '\n' for beer_num in xrange(high,low-1,-1)])
