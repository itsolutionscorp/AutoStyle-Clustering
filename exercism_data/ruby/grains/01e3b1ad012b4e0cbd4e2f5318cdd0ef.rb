class Grains

  def square(id)
    2**(id-1)
  end

  def total()
    sum = 0
    0..64.times {|n| sum += 2**n}
    sum
  end

end
