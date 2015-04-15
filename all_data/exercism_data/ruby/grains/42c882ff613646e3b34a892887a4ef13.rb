class Grains

  def square(num)
    return 2**(num-1);
  end

  def total
    sum = 0
    (0..63).each{ |i| sum += 2**i}
    return sum
  end

end
