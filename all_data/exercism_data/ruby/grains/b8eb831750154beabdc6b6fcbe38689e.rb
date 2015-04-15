class Grains

  TOTAL_SQUARES = 64

  def square(number)
    raise ArgumentError, 'The square number must be 1 or greater' unless number.to_i >= 1

    2 ** (number.to_i - 1)
  end

  def total
    (2 ** TOTAL_SQUARES) - 1
  end
end
