class Grains
  def square(num)
    2**(num - 1)
  end

  def total
    (1..64).map{|num| square(num)}.inject(:+)
  end
end
