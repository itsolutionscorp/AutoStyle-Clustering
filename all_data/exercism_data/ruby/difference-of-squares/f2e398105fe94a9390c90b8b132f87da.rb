class Squares
  def initialize(target)
    @target = target
  end
  def square_of_sums
    result = 0
    (1..@target).each do |number|
      result += number
    end
    result**2
  end
  def sum_of_squares
    result = 0
    (1..@target).each do |number|
      result += number**2
    end
    result
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
