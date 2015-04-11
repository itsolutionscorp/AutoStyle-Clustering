class Fixnum
  def divisble_by?(num)
    self % num == 0
  end
end

module Year
  def self.leap?(year)
    year.divisble_by?(4) && !year.divisble_by?(100) || year.divisble_by?(400)
  end
end
