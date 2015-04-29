class Year
  def self.leap?(year)
    (year.divisible_by?(4) && !year.divisible_by?(100)) || year.divisible_by?(4, 100, 400)
  end
end

class Integer
  def divisible_by?(*numbers)
    numbers.all? { |n| self % n == 0 }
  end
end
