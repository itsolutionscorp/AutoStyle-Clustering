class Squares

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    summables = []
    (1..@num).each do |n|
      summables << (n ** 2)
    end
    summables.inject(:+)
  end

  def square_of_sums
    ((1..@num).to_a.inject(:+)) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
