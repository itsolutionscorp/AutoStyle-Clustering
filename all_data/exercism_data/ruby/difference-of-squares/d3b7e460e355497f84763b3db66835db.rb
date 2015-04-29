class Squares

    attr_reader :square_of_sums
    attr_reader :sum_of_squares

    def initialize(number)
        @sum_of_squares = number * (number + 1) * (2 * number + 1) / 6.0
        @square_of_sums = (number * (1 + number) / 2)**2
    end

    def difference
        @square_of_sums - @sum_of_squares
    end
end
