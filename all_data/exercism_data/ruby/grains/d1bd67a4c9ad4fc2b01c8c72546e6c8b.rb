class Grains

  def square(number)
    2 ** (number - 1)
  end

  def total
    (1..64).reduce { |acc, num| acc += square(num) }
  end

end
