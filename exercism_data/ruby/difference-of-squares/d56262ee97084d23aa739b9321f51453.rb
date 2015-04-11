class Squares
 
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    @sum_of_squares ||= -> { (1..@number).reduce {|a, e| a + e*e } }.call
  end

  def square_of_sums
    @sums ||= -> { (1..@number).reduce {|a, e| a + e } }.call
    @sums * @sums
  end

  def difference
    square_of_sums- sum_of_squares
  end

end
