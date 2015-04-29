class Squares
    def initialize(limit)
        @limit = limit
    end

    def square_of_sums
        1.upto(@limit).reduce(:+)**2
    end

    def sum_of_squares
        1.upto(@limit).reduce {|b, c| b + c**2}
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
