class Year
  def self.leap? year
    year.divisible_by?(400) || ( year.divisible_by?(4) && !year.divisible_by?(100) )
  end
end

class Integer
  def divisible_by? num
    self % num == 0
  end
end
