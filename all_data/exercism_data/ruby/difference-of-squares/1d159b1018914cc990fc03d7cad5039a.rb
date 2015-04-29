
class Squares

    def initialize(number)
        @number = number
    end
    
    def square_of_sums
        (((@number + 1) * @number) / 2) ** 2
    end
    
    def sum_of_squares
        ((2 * (@number ** 3)) + (3 * (@number ** 2)) + @number) / 6
    end 
    
    def difference
        square_of_sums - sum_of_squares 
    end
    
end
