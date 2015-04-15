class Grains
  def square(sq)
    (2**sq) / 2
  end

  def total
    (1..64).map{ |num| square(num) }.inject(:+)
  end
end
