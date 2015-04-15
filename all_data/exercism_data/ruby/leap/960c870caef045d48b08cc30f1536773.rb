# A leap year occurs on every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400
class Year

  def self.leap?(year)
    divisible_by?(4) && century_exception?(year)
  end

  def self.century_exception?(year)
    !divisible_by?(100) || divisible_by?(400)
  end

  def self.divisible_by?(num)
    (self % num).zero?
  end
end
