class Grains
  attr_accessor :chessboard

  def initialize
    @chessboard = (2..64).to_a.each_with_object({1 => 1}) {|square, hsh| hsh[square] = hsh[square-1]*2 }
  end

  def square(space)
    chessboard[space]
  end

  def total
    chessboard.values.reduce(:+)
  end
end
