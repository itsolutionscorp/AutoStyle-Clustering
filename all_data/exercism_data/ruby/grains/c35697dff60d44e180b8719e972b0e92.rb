class Grains

  def square(grain)
    2**(grain-1)
  end

  def total
    sum = 0
    (1..64).each do |i|
      sum += square(i)
    end
    sum
  end

end
