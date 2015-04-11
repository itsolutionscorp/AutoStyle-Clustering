class Grains
  
  def square(i)
    2**(i-1)
    ## The formula to find the amount of grains on tile i of the
    #chessboard is 2^(i-1).
  end

  def total
    sum = 0
    (1..64).each do |place|
      sum += square(place)
    end
    sum
  end
end
