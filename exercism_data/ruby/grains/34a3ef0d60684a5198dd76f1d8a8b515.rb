class Grains
  SQUARES = 64
  def square(number)
    raise ArgumentError unless (1..SQUARES).include?(number)
    2 ** (number - 1)
  end

  def total
    (2 ** SQUARES) - 1
  end
end
