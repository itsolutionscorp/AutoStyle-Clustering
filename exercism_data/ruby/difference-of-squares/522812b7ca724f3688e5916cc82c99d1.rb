class Squares

    @i_in  = 1

    def initialize(n)
        @i_in = n
    end

    def sum_of_squares
        sum = 0
        (1..@i_in).each { |i| sum += i**2 }
        return sum
    end

    def square_of_sums
        sum = 0
        (1..@i_in).each { |i| sum += i }
        return sum**2
    end

    def difference
        return  square_of_sums - sum_of_squares
    end
end
