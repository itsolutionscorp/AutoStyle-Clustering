class Grains
  def square(number)
    2 ** (number - 1)
  end

  def total
    1.upto(64).reduce{ |sum, i| sum + square(i) }
  end
end
