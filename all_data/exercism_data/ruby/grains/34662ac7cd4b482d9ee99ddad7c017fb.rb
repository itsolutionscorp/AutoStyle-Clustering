class Grains
  TOTAL_SQUARES = 64

  def square(number)
    2 ** (number - 1)
  end

  def total
    (1..64).inject { |sum, n| sum + square(n) }
  end
end
