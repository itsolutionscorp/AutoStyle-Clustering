class Squares
    
    def initialize(num)
        @num = num
    end

    def sum_of_squares 
        sum = 0;
        1.step(@num, 1) { |incr|
            sum += incr**2
        } 
        return sum
    end

    def square_of_sums 
        sum = 0;
        1.step(@num, 1) { |incr|
            sum += incr
        }
        return sum**2
    end

    def difference
        return (sum_of_squares - square_of_sums).abs
    end

end
