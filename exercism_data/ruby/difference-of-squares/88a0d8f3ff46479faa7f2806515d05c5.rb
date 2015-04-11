class Squares
    
    def initialize(num)
        @num = num
    end
    
    def sum_of_squares 
        (1..@num).reduce { |result, increment| result + increment**2 } 
    end

    def square_of_sums 
        (1..@num).reduce(:+)**2
    end

    def difference
        return (square_of_sums - sum_of_squares).abs
    end

end
