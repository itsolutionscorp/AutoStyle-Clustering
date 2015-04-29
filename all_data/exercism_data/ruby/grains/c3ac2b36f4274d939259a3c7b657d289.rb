class Grains

  def square(num)
    2 ** num / 2
  end

  def total
    (1..64).collect{|x| 2**x / 2}.inject(:+)
  end

end
