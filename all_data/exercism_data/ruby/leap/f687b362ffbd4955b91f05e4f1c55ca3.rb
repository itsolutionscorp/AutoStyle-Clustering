class Fixnum
  def divisibleBy?(m)
    self % m == 0
  end
end

module Year
  def self.leap?(year)
    (year.divisibleBy?(4) && !(year.divisibleBy?(100))) || year.divisibleBy?(400)
  end
end
