class Squares
    def initialize(num)
        @num = num
        

    end

    def square_of_sums
        sum = 0
        (1..@num).count do |i|
            sum+=i  
        end
        sum**2
    end

    def sum_of_squares
        result = 0
        (1..@num).each do |i|
            result+=i**2
        end
        result
    end

    def difference
        square_of_sums - sum_of_squares
    end

end
