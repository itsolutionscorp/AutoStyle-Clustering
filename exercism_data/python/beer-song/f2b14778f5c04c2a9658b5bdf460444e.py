'''exer beer'''

def song(starting_verse=99, ending_verse=0):
    '''output all or part of song as set by start and end verse'''
    lyrics = ''
    for bottle_num in range(starting_verse, ending_verse - 1, -1):
        lyrics += verse(bottle_num) + '\n'
    return lyrics

def pluralize(num):
    '''return '' if 1, else 's' '''
    if num == 1:
        return ''
    return 's'

def verse(num):
    '''return the verse for the corresponding bottles of beer song'''

    datum = {
        'cbottle': num,
        'cplural': pluralize(num),
        'rbottle': num - 1,
        'rplural': pluralize(num - 1)
    }
    lyric = "%(cbottle)s bottle%(cplural)s of beer on the wall, "\
            "%(cbottle)s bottle%(cplural)s of beer.\n"
    if num == 1:
        lyric += "Take it down and pass it around, no more bottles"
    elif num:
        lyric += "Take one down and pass it around, %(rbottle)s "\
                 "bottle%(rplural)s"
    else:
        lyric = "No more bottles of beer on the wall, no more bottles of "\
                "beer.\nGo to the store and buy some more, 99 bottles"

    lyric += " of beer on the wall.\n"

    return lyric % datum
