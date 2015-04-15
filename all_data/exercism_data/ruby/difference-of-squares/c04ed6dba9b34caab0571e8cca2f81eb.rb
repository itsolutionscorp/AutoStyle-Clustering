class Squares
    def initialize(number)
        @number = number
    end

    def square_of_sums
        a = 0
        (1..@number).each {|num| a += num}
        return a ** 2
    end

    def sum_of_squares
        a = 0
        (1..@number).each {|num| a += num**2}
        return a
    end

    def difference
        return square_of_sums - sum_of_squares
    end




end
