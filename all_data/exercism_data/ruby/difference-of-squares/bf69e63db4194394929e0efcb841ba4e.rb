class Squares
  attr_reader :num
  def initialize(num)
    @num = num
  end

  def square_of_sums
    @square_of_sums ||=  square_sums
  end
  
  def sum_of_squares
    @sum_of_squares ||= sum_squares
  end

  def difference
    @difference ||= calculate_difference 
  end
  
  private

  def square_sums
    ((0..@num).inject(0) { |sum, n| sum += n })**2
  end

  def sum_squares
    (0..@num).inject(0) { |sum, n| sum += n**2 }
  end

  def calculate_difference
    square_of_sums - sum_of_squares
  end
end
