
class Squares

    def initialize(number)
        @number = number
    end
    
    def square_of_sums
        sum = ((@number + 1.0) * @number) / 2.0
        return sum ** 2
    end
    
    def sum_of_squares
        sum = 0
        for i in 1..@number
            sum += i ** 2
        end
        
        return sum
    end 
    
    def difference
        return self.square_of_sums - self.sum_of_squares 
    end
    
end
