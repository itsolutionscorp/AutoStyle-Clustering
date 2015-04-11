class Queens
	BOARD = {
		0 => (0..14).to_a.select{|x| x.even?},
		1 => (16..30).to_a.select{|x| x.even?},
		2 => (32..46).to_a.select{|x| x.even?},
		3 => (48..62).to_a.select{|x| x.even?},
		4 => (64..78).to_a.select{|x| x.even?},
		5 => (80..94).to_a.select{|x| x.even?},
		6 => (96..110).to_a.select{|x| x.even?},
		7 => (112..126).to_a.select{|x| x.even?}
	}
	attr_reader :white, :black
	def initialize(*args)
		argument = args[0]
		@white = argument[:white] if args[0]
		@black = argument[:black] if args[0]
		@white ||= [0,3]
		@black ||= [7,3]
		raise ArgumentError if @white == @black
	end
	def to_s
		board = <<-BOARD.chomp
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
		BOARD
		board[BOARD[@white[0]][@white[1]]] = "W"
		board[BOARD[@black[0]][@black[1]]] = "B"
		board
	end
	def attack?
		case 
		when @white[0] == @black[0]
			true
		when @white[1] == @black[1]
			true
		when @white[0] == @white[1] && @black[0] == @black[1]
			true
		when @white[1]-@white[0] == @black[1]-@black[0]
			true
		when @white[0]-@white[1] == @black[1]-@black[0]
			true
		else
			false
		end
	end
end
