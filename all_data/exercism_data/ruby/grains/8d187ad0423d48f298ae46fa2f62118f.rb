class Grains

  def square(which_square)
    2**(which_square-1)
  end

  def total
    (1..64).map { |i| square(i) }.reduce(:+)
  end

end
