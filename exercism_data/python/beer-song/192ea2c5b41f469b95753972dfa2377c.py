def verse(value):
    song_value_multi =  "REPLACE_ME bottles of beer on the wall, REPLACE_ME bottles of beer.\n" \
                        "Take one down and pass it around, REPLACE_LOWER bottles of beer on the wall.\n"
    song_value_double = "2 bottles of beer on the wall, 2 bottles of beer.\n" \
                        "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    song_value_single = "1 bottle of beer on the wall, 1 bottle of beer.\n" \
                        "Take it down and pass it around, no more bottles of beer on the wall.\n"
    song_value_zero =   "No more bottles of beer on the wall, no more bottles of beer.\n" \
                        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    if(value == 0):
        return song_value_zero
    elif(value == 1):
        return song_value_single
    elif(value == 2):
        return song_value_double
    else:
        return song_value_multi.replace('REPLACE_ME',str(value)).replace('REPLACE_LOWER',str(value-1))

def song(upper,lower=0):
    song_content = ''
    for elm in range(upper,lower-1,-1):
        song_content += verse(elm)
        song_content += "\n"
    return song_content
