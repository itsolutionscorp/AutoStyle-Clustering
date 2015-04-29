class Grains
  TOTAL_SQUARES = 64

  def square(n)
    n > 1 ? square(n - 1) * 2 : 1
  end

  def total
    (1..TOTAL_SQUARES).reduce(0) do |sum, n|
      sum + square(n)
    end
  end
end
