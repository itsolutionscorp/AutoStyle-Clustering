class Grains
  def initialize
    @board = (1...64).map { |n| 2**n }
  end

  def square(num)
    return 1 if num == 1
    @board[num-2]
  end

  def total
    @board.inject(:+) + 1
  end
end
