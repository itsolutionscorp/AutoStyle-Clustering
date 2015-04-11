class Squares
  attr_reader :limit
  def initialize limit
    @limit = limit
  end
  
  def square_of_sums
    sum_of_range**2
  end
  
  #sum of squares = (n(n+1)(2n+1))/6
  def sum_of_squares
    limit*(limit+1)*(2*limit+1)/6
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
  
  private
  # sum of range = (n(n+1))/2
  def sum_of_range
    limit*(limit+1)/2
  end
end
