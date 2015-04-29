class Squares
  attr_reader :limit

  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    sum = (1..limit).inject(&:+)
    sum**2
  end

  def sum_of_squares
    (1..limit).inject(0) do |sum, n|
      sum++ n**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
