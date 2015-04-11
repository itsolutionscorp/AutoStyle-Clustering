class Squares
  class << self

    def new(n)
      @n = n
      self
    end

    def square_of_sums
      (0..@n).to_a.reduce(:+)**2
    end

    def sum_of_squares
      (0..@n).map {|n| n**2}.reduce(:+)
    end

    def difference
      square_of_sums - sum_of_squares
    end

  end
end
