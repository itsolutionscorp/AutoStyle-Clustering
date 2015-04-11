class Grains
  def initialize
    @board = []
    setup
  end

  def square(nth)
    raise ArgumentError if nth < 0

    @board[nth - 1]
  end

  def total
    @board.inject(:+)
  end

  private
  def setup
    @board << 1
    63.times do
      @board << @board.last * 2
    end
  end
end
