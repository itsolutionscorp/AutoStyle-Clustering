Squares = Struct.new(:number) do
  
  def square_of_sums
    sum = (1..number).inject { |sum, i| sum += i }
    sum**2
  end
  
  def sum_of_squares
    (1..number).inject { |sum, i| sum += i**2 }
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
  
end
