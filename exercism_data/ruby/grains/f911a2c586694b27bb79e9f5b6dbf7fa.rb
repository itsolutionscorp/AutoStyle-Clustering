class Grains
  def square(i) 2**(i-1) end 
  ## The formula to find the amount of grains on tile i of the
  #chessboard is 2^(i-1).

  def total
    (1..64).inject {|sum, i| sum + square(i)}
  end
end
