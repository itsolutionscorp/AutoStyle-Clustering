class Queens
	ROW = 0
	COL = 1
	WHITE = 0
	BLACK = 1

	def initialize(positions = {white: [0, 3], black: [7, 3]})
		raise ArgumentError if positions[:white] == positions[:black]
		@queens = [positions[:white], positions[:black]]
		@board = position_queens
	end
	
	def white
		@queens[WHITE]
	end
	
	def black
		@queens[BLACK]
	end
	
	def to_s
		@board.map { |row| row.join(" ") }.join("\n")
	end
	
	def attack?
		same_row || same_col || same_diagonal
	end
	
private

	def position_queens
		position_queen(position_queen(empty_board, WHITE), BLACK)
	end

	def position_queen(board, queen)
		position = @queens[queen]
		color = (queen == WHITE ? "W" : "B")
		board[position[ROW]][position[COL]] = color
		board
	end

	def empty_board
		(0..7).inject([]) do |board, row|
			board << (0..7).inject([]) { |row, value| row << "O" }
		end
	end
	
	def same_row
		white[ROW] == black[ROW]
	end
	
	def same_col
		white[COL] == black[COL]
	end
	
	def same_diagonal
		(white.reduce(:+) == black.reduce(:+)) ||
		(white.reduce(:-) == black.reduce(:-))
	end
end
