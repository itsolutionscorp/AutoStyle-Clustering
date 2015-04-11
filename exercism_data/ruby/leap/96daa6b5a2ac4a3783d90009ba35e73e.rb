class Year
  class << self
    def leap?(year)
      divisible = ->(mod) { (year % mod).zero? }

      divisible[4] && !divisible[100] || divisible[400]
    end
  end
end
