class Grains

  def square(number)
    2 ** (number.to_i - 1)
  end

  def total
    1.upto(64).reduce { |sum, n|
      sum + square(n)
    }
  end
end
