def board(game):
	if board_error(game):
		raise ValueError('invalid board')
	for line in range(1,len(game)-1):
		for tile in range(len(game[line])):
			if game[line][tile]==' ':
				temp = game[line-1][tile-1:tile+2]+game[line][tile-1:tile+2]+game[line+1][tile-1:tile+2]
				bombs = [i for i in temp if i == '*']
				if bombs:
					game[line] = game[line][:tile] + str(len(bombs)) + game[line][tile+1:]
	return game
					
def board_error(game):
	x = len(game[0])
	for line in game[1:-1]:
		if len(line) != x:
			return True
		if line[0] != "|" or line[-1] != "|":
			return True
		for tile in line:
			if tile not in " +-|*":
				return True
	if game[0] != game[-1]:
		return True
	return False
	
