class Grains

  def square(number)
    return 1 if number == 1
    return (1...number).inject(1) { |sum, n| sum*2 } if number > 1
  end

  def total
    ((1..64).map { |n| square(n) }).inject(:+)
  end

end
