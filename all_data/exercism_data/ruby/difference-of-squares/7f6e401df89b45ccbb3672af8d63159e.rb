class Squares
  def initialize(arg)
      @number = arg    
  end
   
  def square_of_sums
    (1..@number).inject(:+)**2
  end

  def sum_of_squares
    (1..@number).inject { |sum, n| sum += n**2 }
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end


end
