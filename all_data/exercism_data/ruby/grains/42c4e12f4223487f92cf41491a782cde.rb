class Grains
  def square(number)
    2**(number - 1)
  end

  def total
    square(65) - 1
  end
end
