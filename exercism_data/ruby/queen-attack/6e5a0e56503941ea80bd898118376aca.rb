class Queens
	attr_reader :white, :black
	
	def initialize(queens = {white: [0,3], black: [7,3]})
		raise ArgumentError if queens[:white] == queens[:black]

		@white = queens[:white]
		@black = queens[:black]
	end

	def to_s
		(0..7).map do |row|
			(0..7).map do |col|
				case [row, col]
				when black
					"B "
				when white
					"W "
				else 
					"_ "
				end
			end.join('').strip
		end.join("\n")
	end

	def attack?
		same_row? || same_col? || same_diagonal?
	end

	private

		def same_row?
			white[0] == black[0]
		end

		def same_col?
			white[1] == black[1]
		end

		def same_diagonal?
			dy = black[1] - white[1]
			dx = black[0] - white[0]
			(dy.to_f / dx).abs == 1.0
		end

end
