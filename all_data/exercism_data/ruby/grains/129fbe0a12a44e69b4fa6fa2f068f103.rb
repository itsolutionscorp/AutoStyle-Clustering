class Grains
  def square(number)
    number <= 2 ? number : 2 ** (number - 1)
  end

  def total
    (0..64).inject{ |sum, number| sum + square(number) }
  end
end
