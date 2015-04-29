class Grains

  TOTAL_SQUARES = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    (1..TOTAL_SQUARES).reduce(0) do | sum, current |
      sum + square(current)
    end
  end
end
