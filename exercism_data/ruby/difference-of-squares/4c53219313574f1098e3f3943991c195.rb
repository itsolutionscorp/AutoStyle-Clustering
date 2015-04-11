class Squares
  def initialize(to)
    @to = to
  end

  def square_of_sums
    integers.inject(:+)**2
  end

  def sum_of_squares
    integers.map {|x| x**2}.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

    def integers
      (1..@to)
    end
end
