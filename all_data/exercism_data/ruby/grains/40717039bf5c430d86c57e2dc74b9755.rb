class Grains
  def square(power)
    2**power - 2**(power - 1)
  end

  def total
    (1..64).inject(0) { |sum, power| sum += 2**power - 2**(power - 1) }
  end
end
