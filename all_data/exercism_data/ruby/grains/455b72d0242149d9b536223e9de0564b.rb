class Grains
  # attr_accessor :square

  def initialize
    @board = fill_board
  end 

  def square(i)
    @board[i-1]
  end

  def total
    @board.reduce(:+)
  end

  private
    def fill_board
      board = Array.new(64)
      board[0] = 1
      board.fill(1) { |i| board[i-1] * 2 }
    end

end
