class Queens

	attr_accessor :white, :black
	
	def initialize(args = {white: [0, 3], black: [7, 3]})
		@white = args[:white]
		@black = args[:black]
		raise ArgumentError.new if args[:black] == args[:white]
	end

	def attack?
		white[0] == black[0] || white[1] == black[1] || (white[0] - black[0]).abs == (white[1] - black[1]).abs
	end
	
	def to_s
		board = ""
		(0..7).each do |row|
			(0..7).each do |column|
				if white == [row, column]
					board += "W " 
				elsif black == [row, column]
					board += "B "
				else
					board += "O "
				end
			end			
			board = board.chop + "\n"
		end
		board.chop
	end
end
