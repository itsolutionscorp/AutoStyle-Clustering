class Squares
    attr_reader :square_of_sums, :sum_of_squares, :difference
    def initialize n
        @square_of_sums = (n*(n+1)/2)**2
        @sum_of_squares = n*(n+1)*(2*n+1)/6
        @difference = self.square_of_sums - self.sum_of_squares
    end
end
