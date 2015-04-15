class Grains
  attr_reader :chess_squares

  def initialize
    setup_board
  end

  def square(n)
    chess_squares[n]
  end

  def total
    chess_squares.reduce(:+)
  end

  private
    def setup_board
      @chess_squares = [0,1]
      (2..64).each do |n|
        chess_squares[n] = chess_squares[n-1]*2
      end
    end
end
