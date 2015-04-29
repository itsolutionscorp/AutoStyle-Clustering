class Squares
  attr_reader :integer

    def initialize(integer)
      @integer = integer
    end

    def square_of_sums
      sum = (1..integer).inject { |sum, i| sum + i }
      sum**2
    end

    def sum_of_squares
      (1..integer).inject { |sum, i| sum + i**2 }
    end

    def difference
      square_of_sums - sum_of_squares
    end

end
