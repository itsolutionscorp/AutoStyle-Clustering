class Year
  def self.leap?(year)
    year.divisible_by?(4) && (!year.divisible_by?(100) || year.divisible_by?(400))
  end
end

class Fixnum
  def divisible_by?(x)
    self % x == 0
  end
end
