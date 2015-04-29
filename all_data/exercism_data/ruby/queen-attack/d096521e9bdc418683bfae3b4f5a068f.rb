class Queens
	attr_reader :white, :black

	BOARD_SIZE = 8
	DEFAULT_WHITE_POSITION = [0,3]
	DEFAULT_BLACK_POSITION = [7,3]
	
	def initialize(white: DEFAULT_WHITE_POSITION, black: DEFAULT_BLACK_POSITION)
		raise ArgumentError, "Can't occupy same position" if white == black

		@white = white
		@black = black
	end

	def to_s
		BOARD_SIZE.times.map { |row|
			BOARD_SIZE.times.map { |col|
				symbol_for(row, col)
			}.join(" ")
		}.join("\n")
	end

	def attack?
		same_row? || same_col? || same_diagonal?
	end

	private

		def symbol_for(row, col)
			case [row, col]
			when black then "B"
			when white then "W"
			else            "_"
			end
		end

		def same_row?
			white[0] == black[0]
		end

		def same_col?
			white[1] == black[1]
		end

		def same_diagonal?
			dy = black[1] - white[1]
			dx = black[0] - white[0]
			dy.abs == dx.abs
		end

end
