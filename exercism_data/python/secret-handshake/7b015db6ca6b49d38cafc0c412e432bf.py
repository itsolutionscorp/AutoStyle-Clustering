def handshake(code):
	return Handshake(code).moves()

def code(moves):
	return Handshake(moves).strcode()

class Handshake(object):

	def __init__(self, code_or_moves):
		if isinstance(code_or_moves, list):
			self.code = Handshake.moves_to_code(code_or_moves)
		elif not isinstance(code_or_moves, int):
			self.code = Handshake.code_to_int(code_or_moves)
		else:
			self.code = code_or_moves
		if self.code < 0:
			self.code = 0

	def moves(self):
		result = [move for move, mask in Handshake._moves if self.code&mask and move != '!reverse']
		if self.code & Handshake._move_to_mask['!reverse']:
			return list(reversed(result))
		return result

	def strcode(self):
		return Handshake.code_to_str(self.code)

	@staticmethod
	def moves_to_code(moves):
		result = 0
		for move in moves:
			if move not in Handshake._move_to_mask:
				return 0
			result |= Handshake._move_to_mask[move]

		check = Handshake(result).moves()
		if check == moves:
			return result
		elif list(reversed(check)) == moves:
			return result | Handshake._move_to_mask['!reverse']
		else:
			return 0

	@staticmethod
	def code_to_int(strcode):
		result = 0
		for l in strcode:
			result <<= 1
			if l == '1':
				result += 1
			elif l != '0':
				return 0
		return result

	@staticmethod
	def code_to_str(intcode):
		result = "".join('1' if intcode & mask else '0' for move, mask in reversed(Handshake._moves)).lstrip('0')
		if len(result) == 0:
			result = '0'
		return result

	# init class constants
	_moves = []
	_move_to_mask = {}
	mask = 1
	for move in ['wink', 'double blink', 'close your eyes', 'jump', '!reverse']:
		_moves.append((move, mask))
		_move_to_mask[move] = mask
		mask <<= 1
	del mask, move
