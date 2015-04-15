class Grains

  TOTAL_SQUARES = 64

  def square(number)
    2 ** (number.to_i - 1)
  end

  def total
    1.upto(TOTAL_SQUARES).reduce { |sum, n|
      sum + square(n)
    }
  end
end
