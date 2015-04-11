class Grains
  def square(num)
    (1...num).inject(1) { |sum, n| sum *= 2 }
  end

  def total
    (1..64).inject(1) { |sum, n| sum += square(n) } - 1
  end
end
