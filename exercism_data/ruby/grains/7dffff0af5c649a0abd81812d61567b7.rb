class Grains
  attr_reader :board

  def initialize
    @board = [1]

    64.times do |index|
      next if index == 0
      previous_square_amount = @board[index - 1]
      @board <<  previous_square_amount * 2
    end
  end

  def square(num)
    board[num - 1]
  end

  def total
    board.reduce(&:+)
  end
end
