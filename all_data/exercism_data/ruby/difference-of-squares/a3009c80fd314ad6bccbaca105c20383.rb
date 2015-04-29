class Squares

  def initialize(value)
    @value = value
  end

  def square_of_sums
    1.upto(@value).reduce(:+).abs2 
  end

  def sum_of_squares
    1.upto(@value).reduce { |tally, item| tally + item.abs2 }
  end

  def difference
    square_of_sums - sum_of_squares  
  end
end
