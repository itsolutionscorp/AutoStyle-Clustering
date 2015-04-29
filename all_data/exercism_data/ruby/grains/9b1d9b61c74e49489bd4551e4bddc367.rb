class Grains
  TOTAL_SQUARES = 64

  def square(num)
    2**(num-1)
  end

  def total
    (1..TOTAL_SQUARES).inject { |sum, n| sum + square(n) }
  end
end
