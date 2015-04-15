class Grains
    CHESSBOARD = 64
    
    def initialize(board_size=CHESSBOARD)
	@board_size = board_size
    end

    def square(number)
	raise "Out of Board" if (number < 1) or 
				(number > @board_size)
        1 << (number - 1)
    end

    def total
        (1 << @board_size) - 1
    end
end
