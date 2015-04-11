module Year
  class << self
    def leap?(year)
      return false if (divisible_by_100?(year) && !divisible_by_400?(year))
      divisible_by_4?(year)
    end

    private

    def divisible_by_100?(year)
      year % 100 == 0
    end

    def divisible_by_400?(year)
      year % 400 == 0
    end

    def divisible_by_4?(year)
      year % 4 == 0
    end
  end
end
