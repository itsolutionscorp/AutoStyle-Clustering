class Squares
  def initialize(number)
    @numbers = (1..number)
  end

  def sum_of_squares
    @sum_of_squares ||= sum_of(squares_of(@numbers))
  end

  def square_of_sums
    @square_of_sums ||= sum_of(@numbers) ** 2
  end

  def difference
    @difference ||= square_of_sums - sum_of_squares
  end

  private

    def sum_of(numbers) # 'sum' is singular: returns single value
      numbers.reduce(:+)
    end

    def squares_of(numbers) # 'squares' is plural: returns multiple values
      @squares ||= numbers.map {|n| n**2}
    end
end
