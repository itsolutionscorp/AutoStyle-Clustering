class Grains
  def square(square_num)
    2**(square_num - 1)
  end
  def total
    (1..64).reduce { |sum, square_num| sum + square(square_num) }
  end
end
