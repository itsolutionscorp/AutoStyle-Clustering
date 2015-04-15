# Strategy 2: calculate sums of squares
class Grains
  def square(i)
    sum_to(i) - sum_to(i - 1)
  end

  def total
    sum_to 64
  end

  private

  def sum_to(i)
    (1 << i) - 1
  end
end
