class Squares
  attr_reader :users_number, :sums, :squares

  def initialize(users_number)
    @users_number = users_number
    @sums, @squares = 0, 0
  end

  def square_of_sums
    sum = ->(num) do
      if num < 1
        sums ** 2
      else
        @sums += num and sum[num - 1]
      end
    end
    sum[users_number]
  end

  def sum_of_squares
    square = ->(num) do
      if num < 1
        squares
      else
        @squares += num ** 2 and square[num - 1]
      end
    end
    square[users_number]
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
