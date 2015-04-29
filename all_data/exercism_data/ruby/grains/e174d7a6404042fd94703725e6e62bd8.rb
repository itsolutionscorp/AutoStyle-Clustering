class Grains
  def square(number)
    2**(number.pred)
  end

  def total
    square(65).pred
  end
end
