class Year
  class << self
    def leap?(year)
      divisible_by(4, year) &&
        (!divisible_by(100, year) || divisible_by(400, year))
    end

    private

    def divisible_by(divisor, number)
      number % divisor == 0
    end
  end
end
