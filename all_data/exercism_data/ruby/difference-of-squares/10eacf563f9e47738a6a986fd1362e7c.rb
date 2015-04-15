class Squares
  def initialize(input)
    @input = input
  end

  def square_of_sums
    index = 0
    value = 0
    while index <= @input do
      value += index
      index +=1
    end
    value**2
  end

  def sum_of_squares
    index = 0
    value = 0
    while index <= @input do
      value += index**2
      index +=1
    end
    value
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
