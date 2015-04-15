class Squares
    @number
    @square_of_sums
    @sum_of_squares

    def initialize(number)
        @number = number
    end

    def square_of_sums
        return @square_of_sums if @square_of_sums
        @square_of_sums = (1..@number).inject(:+)**2
    end

    def sum_of_squares
        return @sum_of_squares if @sum_of_squares
        @sum_of_squares = (1..@number).map{ |n| n**2 }.inject(:+)
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
