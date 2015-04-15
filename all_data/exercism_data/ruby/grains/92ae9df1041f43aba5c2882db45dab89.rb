class Grains
  
  def initialize
    @board ||= ChessBoard.new
  end
  
  def square(num)
    @board.square(num)
  end

  def total
    @board.squares.inject(:+)
  end
end

class ChessBoard
  
  def initialize
    @board ||= [1]
    populate_board
  end

  def populate_board
    64.times do |num|
      @board[num] = @board[num-1]*2 if num > 0
    end
  end

  def square(num)
    @board[num-1]
  end

  def squares
    @board
  end

end
