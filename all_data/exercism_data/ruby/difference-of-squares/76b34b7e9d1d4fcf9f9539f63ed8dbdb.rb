class Squares
    def initialize(num)
        @upper_bound = num
    end

    def square_of_sums
        ((1..@upper_bound).inject(0) { |sum, n| sum + n })**2
    end

    def sum_of_squares
        (1..@upper_bound).inject(0) { |sum, n| sum + n**2 }
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
