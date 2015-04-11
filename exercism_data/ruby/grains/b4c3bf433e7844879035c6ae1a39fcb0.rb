class Grains
  def initialize
    @board = generate_board
    @total = @board.inject{ |sum, x| sum += x }
  end

  def square(number)
    @board[number - 1]
  end

  def total
    @total
  end

  def generate_board
    board_array = []
    (0...64).each do | n |
      board_array << (2 ** n)
    end
    board_array
  end
end
