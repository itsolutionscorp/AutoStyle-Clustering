class Squares
    def initialize(n)
        @n = n
    end

    def square_of_sums
        return (1..@n).to_a.inject(:+)**2
    end

    def sum_of_squares
        return (1..@n).to_a.map { |x| x**2 }.inject(:+)
    end

    def difference
        return square_of_sums - sum_of_squares
    end
end
