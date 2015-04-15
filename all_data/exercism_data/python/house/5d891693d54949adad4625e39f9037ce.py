full = '''This is the horse and th1 hound and th1 horn
that belonged to the farmer sowing his corn
that kept the rooster that crowed in th1 morn
that woke the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with th1 crumpled horn
that tossed the dog
that worried the cat
that killed the rat
that ate the malt
that lay in the house that Jack built.'''

lines = full.split('the')
this_is = lines.pop(0)
lines = [l.replace('1','e') for l in lines]

def rhyme():
	song = []
	for i in range(len(lines)):
		song.append('the'.join([this_is] + lines[-(i+1):]))
	return '\n\n'.join(song)
