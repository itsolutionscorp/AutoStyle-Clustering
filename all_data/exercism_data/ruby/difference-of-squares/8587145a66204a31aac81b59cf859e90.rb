class Squares
  attr_reader :integer

    def initialize(integer)
      @integer = integer
    end

    def square_of_sums
      sum = 0
      (0..integer).each { |i| sum += i }
      sum**2
    end

    def sum_of_squares
      sum = 0
      (0..integer).each { |i| sum += i**2 }
      sum
    end

    def difference
      square_of_sums - sum_of_squares
    end

end
