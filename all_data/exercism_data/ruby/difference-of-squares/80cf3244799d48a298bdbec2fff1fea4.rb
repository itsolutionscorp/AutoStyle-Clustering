class Squares
    attr_reader :limit

    def initialize limit
        @limit = limit
    end

    def sum_of_squares
        (1..limit).reduce(0) { |sum, number| sum + number ** 2 }
    end

    def square_of_sums
        (1..limit).reduce(:+) ** 2
    end

    def difference
        (sum_of_squares - square_of_sums).abs
    end
end
