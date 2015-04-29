class Grains

  def square(number)
    2**(number-1)
  end

  def total
    1.upto(64).map{|n| square(n)}.reduce(:+)
  end

end
