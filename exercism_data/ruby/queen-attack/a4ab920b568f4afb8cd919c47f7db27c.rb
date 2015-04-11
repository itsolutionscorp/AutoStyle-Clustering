class Queens
	attr_reader :white, :black
	DEFAULT_POSITIONS = {white: [0, 3], black: [7, 3]}
	
	def initialize(hash = DEFAULT_POSITIONS)
		@board = Array.new(8){ Array.new(8){"O"}}
		@white = hash[:white]
		@black = hash[:black]
		raise ArgumentError if @white == @black
	end

	def to_s
		set_position(@white, "W")
		set_position(@black, "B")
		@board.map {|row| row.join(' ')}.join("\n")
	end

	def attack?
		return true if @white[0] == @black[0] || @white[1] == @black[1] #vertical/horizontal
		return true if (white[0] - black[0]).abs == (white[1] - black[1]).abs #diag
	end

	private

		def set_position(color, placeholder)
			@board[color[0]][color[1]] = placeholder
		end

end
