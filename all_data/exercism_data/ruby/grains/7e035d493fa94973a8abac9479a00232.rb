class Grains
  def square(i)
    2 ** (i - 1)
  end

  def total
    (0..63).reduce(0){|t,i| t + (2**i)}
  end
end
