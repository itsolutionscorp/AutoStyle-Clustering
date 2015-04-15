# class Year

#   def self.leap?(year)
#     self.divisible_four?(year) && (!self.divisible_hundred?(year) || self.divisible_fourhundred?(year))
#   end

#   def self.divisible_four?(year)
#     year % 4 == 0
#   end

#   def self.divisible_hundred?(year)
#     year % 100 == 0
#   end

#   def self.divisible_fourhundred?(year)
#     year % 400 == 0
#   end

# end

class Year
  def self.leap?(number)
    Year.new(number).leap?
  end

  def leap?
    divisible_by?(400) || divisible_by?(4) && !divisible_by?(100)
  end

  private

  def divisible_by?(i)
    (self % i) == 0
  end
end
