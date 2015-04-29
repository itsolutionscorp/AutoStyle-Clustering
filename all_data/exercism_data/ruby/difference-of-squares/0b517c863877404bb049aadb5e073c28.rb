class Squares
  
  attr_reader :square_of_sums, :sum_of_squares

  def initialize(num_to_sum_to)
    @num_total, @sum_of_squares = 1,1
    @num_to_sum_to = num_to_sum_to
    calculate
  end

  def difference()
    @square_of_sums  - @sum_of_squares   
  end

  private 

  def calculate
    (2..@num_to_sum_to).each do |num|
      @num_total+=num
      @sum_of_squares+=(num**2)
    end   
    @square_of_sums  = (@num_total**2)
  end
end
