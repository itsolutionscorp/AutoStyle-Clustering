class Grains
  def square(number)
    2**(number - 1)
  end

  def total
    (0..63).to_a.reduce(0) { |a, e| a + 2**e }
  end
end
