Squares = Struct.new(:number) do

  def square_of_sums
    @square_os ||= (1..number).reduce(:+)**2
  end

  def sum_of_squares
    @sum_os ||= (1..number).inject { |sum, n| sum += n**2 }
  end

  def difference
    @diff ||= square_of_sums - sum_of_squares
  end

end
