class Grains
  MAX_SQUARES = 64
  def square number
    return 1 if number == 1
    2 ** (number - 1)
  end

  def total
    (2 ** MAX_SQUARES)-1
  end
end
