class Grains
  
  def square n
    2 ** (n-1)
  end

  def total
    square(65) - 1
    #(1..64).inject {|x, y| x + square(y)}
    #seems less fun this way :(
  end

end
