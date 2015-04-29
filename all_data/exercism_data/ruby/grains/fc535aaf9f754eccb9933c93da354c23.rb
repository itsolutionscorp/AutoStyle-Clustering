class Grains
  attr_accessor :board

  def initialize
    @board = Array.new(64)
    fill_board
  end

  def square(n)
    # 2**(n - 1)
    fail ArgumentError if n > 64
    board[n - 1]
  end

  def total
    board.reduce(:+)
  end

  private

  def fill_board
    board[0] = 1
    1.upto(63).each do |i|
      board[i] = board[i - 1] * 2
    end
  end
end
