class Grains


  def initialize

  end

  def square(num)
    2**(num-1)
  end

  def total
    (1..64).reduce { |sum, n| sum + square(n) }
  end

end
