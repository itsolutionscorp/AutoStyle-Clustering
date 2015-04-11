class Grains
  
  def initialize
    @chessboard = [ *(0..63).collect { |exp| 2**exp } ]
  end

  def square n
    @chessboard[n-1]
  end

  def total
    @chessboard.reduce(:+)
  end
end
