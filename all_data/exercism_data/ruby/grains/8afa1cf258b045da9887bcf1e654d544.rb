class Grains
  NUMBER_OF_SQUARES = 64

  def square(index)
    2 ** (index - 1)
  end

  def total
    square(NUMBER_OF_SQUARES + 1) - 1
  end
end
