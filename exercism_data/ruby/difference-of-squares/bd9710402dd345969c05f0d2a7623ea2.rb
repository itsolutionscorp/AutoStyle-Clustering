class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum(numbers) ** 2
  end

  def sum_of_squares
    sum(square(numbers))
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def numbers
    (1..@number)
  end

  def sum(nums)
    nums.inject(:+)
  end

  def square(nums)
    nums.map { |number| number ** 2 }
  end
end
