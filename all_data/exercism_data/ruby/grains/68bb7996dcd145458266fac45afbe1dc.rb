class Grains
  def total(num=64)
    (1..num).map{|rice| square(rice) }.inject(:+)
  end
  def square(num)
    2**(num-1)
  end
end
