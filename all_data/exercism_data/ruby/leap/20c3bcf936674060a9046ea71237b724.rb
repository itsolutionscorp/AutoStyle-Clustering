module Year
  class << self
    def leap?(year)
      divisible_by?(year, 4) &&
        !divisible_by?(year, 100) ||
        divisible_by?(year, 400)
    end

    private

    def divisible_by?(numerator, denominator)
      numerator % denominator == 0
    end
  end
end
