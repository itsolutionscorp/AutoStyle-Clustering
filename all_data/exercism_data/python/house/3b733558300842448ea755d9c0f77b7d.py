def rhyme():
    return '\n\n'.join(verse(i) for i in range(12))

def verse(n):
    occ = [
    ['','the horse and the hound and the horn'],
    ['belonged to ','the farmer sowing his corn'],
    ['kept ','the rooster that crowed in the morn'],
    ['woke ','the priest all shaven and shorn'],
    ['married ','the man all tattered and torn'],
    ['kissed ','the maiden all forlorn'],
    ['milked ','the cow with the crumpled horn'],
    ['tossed ','the dog'],
    ['worried ','the cat'],
    ['killed ','the rat'],
    ['ate ','the malt'],
    ['lay in ','the house that Jack built.']][::-1]
    m = len(occ)
    return 'This is '+occ[n][1] +''.join('\nthat ' + occ[i][0]+occ[i][1] for i in range(n)[::-1]) 
