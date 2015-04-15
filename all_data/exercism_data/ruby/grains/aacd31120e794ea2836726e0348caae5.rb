class Grains
  def square(i) 2**(i-1) end 
  ## The formula to find the amount of grains on tile i of the
  #chessboard is 2^(i-1).

  def total
    squares = (1..64).map{|i| square(i)}
    squares.inject(:+)
  end
end
