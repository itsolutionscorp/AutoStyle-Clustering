class Grains
  def square number
    return 1 if number == 1
    2 ** (number - 1)
  end

  def total
    (2 ** 64)-1
  end
end
