class Grains
  def square(square_number)
    2 ** (square_number-1)
  end

  def total
    1.upto(64).inject(0) { |sum, n| sum += square(n) }
  end
end
