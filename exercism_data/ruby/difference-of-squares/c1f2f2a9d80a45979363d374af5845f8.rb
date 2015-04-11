class Squares
    @@integer = 0

    def initialize(int)
        @@integer = int
    end

    def square_of_sums
        sum = (1..@@integer).to_a.inject(:+)
        sum * sum
    end

    def sum_of_squares
        (1..@@integer).to_a.map {|x| x * x}.inject(:+)
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
