BEER_SONG_SOURCE = 'beer_song.txt'
with open(BEER_SONG_SOURCE, 'r') as input_file:
    BEER_SONG_VERSES = input_file.read().split('\n\n')

def song(start, end=0):
	return '\n\n'.join(BEER_SONG_VERSES[99-start:100-end]) + '\n\n'

def verse(number):
	return BEER_SONG_VERSES[99-number] + '\n'
