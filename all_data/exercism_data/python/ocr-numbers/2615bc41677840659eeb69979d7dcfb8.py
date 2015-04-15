segmentsInNumber = [
[1, 1, 1, 1, 1, 1, 0], #0
[0, 1, 1, 0, 0, 0, 0], #1
[1, 1, 0, 1, 1, 0, 1], #2
[1, 1, 1, 1, 0, 0, 1], #3
[0, 1, 1, 0, 0, 1, 1], #4
[1, 0, 1, 1, 0, 1, 1], #5
[1, 0, 1, 1, 1, 1, 1], #6
[1, 1, 1, 0, 0, 0, 0], #7
[1, 1, 1, 1, 1, 1, 1], #8
[1, 1, 1, 1, 0, 1, 1]  #9
]

segmentPositions = [(0, 1), (1, 2), (2, 2), (2, 1), (2, 0), (1, 0), (1, 1)]
enabledInput = '_||_||_'

def grid(input):
	input = int(input)
	
	if (input < 0 or input > 9):
		raise ValueError("Invalid input number. Must be in range of 0-9")
		
	correctSegments = segmentsInNumber[input]
	
	builtGrid = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']]
	
	for i, pos in enumerate(segmentPositions):
		if correctSegments[i]:
			builtGrid[pos[0]][pos[1]] = enabledInput[i]
			
	return [''.join(x) for x in builtGrid]

def segmentStatus(number, segment):
	return segmentsInNumber[number][segment]
	
def string_to_segments(input):
	segments = [0, 0, 0, 0, 0, 0, 0]
	
	#Validate garbage
	if input[0][0] != ' ': return None
	if input[0][2] != ' ': return None
	
	for i, pos in enumerate(segmentPositions):
		if not input[pos[0]][pos[1]] in (enabledInput[i], ' '): return None
	
	#Get status
	for i, pos in enumerate(segmentPositions):
		segments[i] = input[pos[0]][pos[1]] == enabledInput[i]
		
	return segments
	
def number(input):
	if len(input) != 4:
		raise ValueError("Insufficient Rows")
		
	for y, row in enumerate(input):
		if len(row) != 3:
			raise ValueError("Incorrect row length on row {}.".format(y))
			
	#Validate last row empty
	for char in input[3]:
		if (char != ' '):
			return '?' #garbage
			
	segments = string_to_segments(input)
	
	if segments == None:
		return '?' #garbage
	
	for number, segmentPerNumber in enumerate(segmentsInNumber):
		if segments == segmentPerNumber:
			return str(number)
	
	return '?' #garbage
