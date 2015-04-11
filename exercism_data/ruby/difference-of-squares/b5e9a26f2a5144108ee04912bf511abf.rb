class Squares

  def initialize(value_of_n)
    @value_of_n = value_of_n
  end

  def square_of_sums
    (1..@value_of_n).inject(0, :+) {|sum,value| value } ** 2 
  end

  def sum_of_squares
    (1..@value_of_n).inject(0) { |sum,value| sum + value ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
