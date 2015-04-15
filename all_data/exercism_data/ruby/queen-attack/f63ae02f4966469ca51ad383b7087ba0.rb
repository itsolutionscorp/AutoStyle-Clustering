class Queens
	attr_reader :white, :black

	def initialize(white: [0, 3], black: [7, 3])
		raise(ArgumentError) if white == black
		@white = white
		@black = black
	end

	def to_s()
		board = Array.new(8) {|_| Array.new(8, '_')}

		x, y = white
		board[x][y] = 'W'

		x, y = black
		board[x][y] = 'B'

		board.collect{|r| r.join(' ')}.join("\n")
	end

	def attack?()
		white[0] == black[0] || white[1] == black[1] || (white[0]-black[0]).abs == (white[1]-black[1]).abs
	end
end
