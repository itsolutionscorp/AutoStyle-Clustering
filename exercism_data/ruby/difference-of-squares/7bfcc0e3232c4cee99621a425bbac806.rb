class Squares
 
  def initialize limit
    @sum_of_squares = 0
    sum=0

    (1 .. limit).each do |i| 
      @sum_of_squares += i*i
      sum += i
    end

    @square_of_sums=sum*sum

    @difference = (@square_of_sums - @sum_of_squares).abs

  end

  attr_accessor :square_of_sums, :sum_of_squares, :difference
end
