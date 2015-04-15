class Grains

  def square(square_number)
    2 ** (square_number - 1)
  end

  def total
    1.upto(64).reduce(0) {|sum, i| sum + square(i)}
  end

end
