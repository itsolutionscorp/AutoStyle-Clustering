class Grains
  attr_reader :total, :board
  def initialize
    @board =  (0..63).map { |i| 2**i }
    @total = @board.inject(0) { |sum,s| sum + s }
  end
  
  def square(n)
    @board[n-1]
  end
end
