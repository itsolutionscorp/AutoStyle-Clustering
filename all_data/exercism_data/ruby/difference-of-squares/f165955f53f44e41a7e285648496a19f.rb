class Squares

  def initialize(numbers) 
    @numbers = numbers
    @sum = 0 
    @square = 0
  end 

  def sum_of_squares
    (1..@numbers).each do |num| 
      @sum += (num**2)
    end
    @sum
  end

  def square_of_sums
    (1..@numbers).each do |num| 
      @square += num 
    end
    @square**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
