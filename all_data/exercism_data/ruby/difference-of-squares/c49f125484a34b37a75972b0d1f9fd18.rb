class Squares
    @number
    @square_of_sums
    @sum_of_squares

    def initialize(number)
        @number = number
    end

    def square_of_sums
        return @square_of_sums if @square_of_sums
        sum = (1..@number).reduce 0 do |acc, item|
            acc += item
        end
        @square_of_sums = sum**2
    end

    def sum_of_squares
        return @sum_of_squares if @sum_of_squares
        @sum_of_squares = (1..@number).map do |n|
            n ** 2
        end.reduce 0 do |acc, item|
            acc += item
        end
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
