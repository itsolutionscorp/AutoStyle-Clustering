class Squares
    attr_reader :square_of_sums, :sum_of_squares, :difference
    def initialize number
        @square_of_sums = (1..number).inject(:+)**2
        @sum_of_squares = (1..number).map{|x| x**2}.inject(:+)
        @difference = self.square_of_sums - self.sum_of_squares
    end
end
