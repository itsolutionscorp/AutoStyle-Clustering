class Integer
  def divisible_by? number
    self % number == 0
  end
end

class Year
  def self.leap?(year)
    year.divisible_by? 4 and not year.divisible_by? 100 or
        year.divisible_by? 400
  end
end
