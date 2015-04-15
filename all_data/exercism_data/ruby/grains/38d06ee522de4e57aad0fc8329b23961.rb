class Grains
  def initialize
    @board = Array.new(64) { |index| 2**index }
  end

  def square(num)
    board[num - 1]
  end

  def total
    board.reduce(&:+)
  end

  private

  attr_reader :board
end
