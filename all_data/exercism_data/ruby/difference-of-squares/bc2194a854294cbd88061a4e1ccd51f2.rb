class Squares  
  attr_reader :limit, :sum, :sum_of_squares
  
  def initialize(limit)
    @limit = limit
    @sum = 0
    @sum_of_squares = 0

    limit.times do |i|
      @sum += (i + 1)
      @sum_of_squares += (i + 1)**2 
    end     
  end

  def square_of_sums   
    sum ** 2
  end
    
  def difference
    square_of_sums - sum_of_squares
  end    
end
