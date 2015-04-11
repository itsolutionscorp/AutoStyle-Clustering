class Squares

    def initialize(num)
        @num = num
    end

    def square_of_sums
        self.square(1.upto(@num).inject { |acc, i| acc + i })
    end

    def sum_of_squares
        1.upto(@num).inject { |acc, i| acc + self.square(i) }
    end

    def difference
        square_of_sums - sum_of_squares
    end

    def square(n)
        n**2
    end
end
