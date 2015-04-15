class Squares
  def initialize(num)
    @num = num
  end

    def square_of_sums
      sum = 0
      for n in 1..@num
        sum += n
      end
      sum = sum**2     
    end
    
    def sum_of_squares
      sum = 0
      for n in 1..@num
        sum += (n**2)
      end
      return sum
    end
    
    def difference
      square_of_sums - sum_of_squares
    end
  


end
