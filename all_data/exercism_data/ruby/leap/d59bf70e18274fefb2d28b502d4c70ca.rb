class Year
  class << self
    def leap?(year)
      # it's a leap year if it's divisible by 4 but not 100, or
      # if it's divisible by 400
      (divisible?(year, 4) && !divisible?(year, 100)) || divisible?(year, 400)
    end

    private

    def divisible?(year, divisor)
      year.modulo(divisor).zero?
    end
  end
end
