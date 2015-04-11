class Grains
  def square(number)
    2**(number-1)
  end

  def total
    (0..63).reduce(0) { |result,number| result+= 2**number }
  end
end
