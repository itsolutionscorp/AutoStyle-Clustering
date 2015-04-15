class Squares
    def initialize(n)
        @n = n
    end

    def square_of_sums
        return 1.upto(@n).reduce(:+)**2
    end

    def sum_of_squares
        return 1.upto(@n).map { |x| x**2 }.reduce(:+)
    end

    def difference
        return square_of_sums - sum_of_squares
    end
end
