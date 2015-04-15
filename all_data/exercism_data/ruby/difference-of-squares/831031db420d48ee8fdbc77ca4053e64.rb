class Squares
    attr_reader :square_of_sums, :sum_of_squares, :difference

    def initialize(limit)
        numbers = (1..limit)
        @sum_of_squares = numbers.map { |number| number**2 }.reduce(:+)
        @square_of_sums = numbers.reduce(:+)**2
        @difference = square_of_sums - sum_of_squares 
    end
end
