module Year
  class << self
    def leap?(year)
      if divisible_by?(year, 4)
        if divisible_by?(year, 100)
          if divisible_by?(year, 400)
            return true
          else
            return false
          end
        end
        return true
      end
    end

    private

    def divisible_by?(numerator, denominator)
      numerator % denominator == 0
    end
  end
end
