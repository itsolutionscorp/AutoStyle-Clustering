class Grains
  NUMBER_OF_SQUARES = 64
  
  def square(n)
    2 ** (n - 1)
  end

  def total
    (1..NUMBER_OF_SQUARES).map{|n| square(n)}.inject(:+)
  end
end
