class Squares
  
  attr_reader :limit

  def initialize(limit)
    
    @limit = limit
  
  end

  def sum_of_squares
  
    ((1..limit).map {|e| e**2}).inject(:+)
  
  end

  def square_of_sums
    
    (1..limit).inject(:+) ** 2
    
  end

  def difference
    
    square_of_sums - sum_of_squares
    
  end

end
