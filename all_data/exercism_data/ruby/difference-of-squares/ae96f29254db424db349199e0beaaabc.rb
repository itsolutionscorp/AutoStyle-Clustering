class Squares
  attr_reader :users_number, :sums, :squares

  def initialize(users_number)
    @users_number = users_number
    @sums, @squares = 0, 0
  end

  def square_of_sums(num = users_number)
    if num < 1
      sums ** 2
    else
      @sums += num
      square_of_sums(num - 1)
    end
  end

  def sum_of_squares(num = users_number)
    if num < 1
      squares
    else
      @squares += num ** 2
      sum_of_squares(num - 1)
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
