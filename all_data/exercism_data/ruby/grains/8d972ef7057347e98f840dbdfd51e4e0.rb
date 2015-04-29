class Grains

  def initialize
    @board = Array.new(65) { 0 }
  end

  def square(index)
    return @board[index] if @board[index] > 0
    if index == 1
      @board[index] = 1
      return 1
    end
    @board[index-1] = 2 * square(index - 1)
  end

  def total
    square(64)
    @board.inject(1) {|sum, v| sum + v}
  end
end
