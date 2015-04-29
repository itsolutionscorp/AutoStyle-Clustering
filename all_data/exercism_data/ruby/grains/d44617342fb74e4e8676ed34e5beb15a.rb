class Grains
  def square chess_square
    2 ** (chess_square - 1)
  end

  def total
    [* 1..64].map { |chess_square| square chess_square }.inject(:+)
  end
end
