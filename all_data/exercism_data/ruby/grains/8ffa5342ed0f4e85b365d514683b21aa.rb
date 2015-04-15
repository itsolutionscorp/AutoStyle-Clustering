class Grains
  def initialize
    @board = [1]
    build_board
  end

  def square(index)
    @board[index-1] #0 based index
  end

  def total
    @board.inject:+
  end

  def build_board
    64.times { |i| @board << (@board[i-1] * 2) unless i == 0  }
  end

end
