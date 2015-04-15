class Grains
  def initialize
    @chessboard = Hash.new
    (1..64).each { |i| @chessboard[i] = 2 ** (i-1) }
  end

  def square(n)
    @chessboard[n]
  end

  def total
    @chessboard.values.inject(:+)
  end
end
