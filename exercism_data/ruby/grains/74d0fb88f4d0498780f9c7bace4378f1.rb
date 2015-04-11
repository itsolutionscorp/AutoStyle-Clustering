class Grains

  def initialize
  end

  def square(num)
    num == 1 ? 1 : 2**(num-1)
  end

  def total
    (1..64).inject { |sum, n| sum + square(n) }
  end

end

mygrains = Grains.new.total
puts mygrains == 18446744073709551615
