class Grains
  SQUARES_ON_BOARD = 1..64

  def square(n)
    2 ** (n - 1)
  end

  def total
    SQUARES_ON_BOARD.map {|s| square(s)}.reduce(:+)
  end
end
