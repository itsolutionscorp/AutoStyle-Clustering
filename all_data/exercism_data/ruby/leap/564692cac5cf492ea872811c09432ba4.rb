class Year
  class << self
    def leap?(year)
      @@year = year
      (divisible_by_four? && !century?) || divisible_by_fourhundred?
    end

    def divisible_by_four?
      @@year % 4 == 0
    end

    def century?
      @@year % 100 == 0
    end

    def divisible_by_fourhundred?
      @@year % 400 == 0
    end
  end
end
