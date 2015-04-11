class Fixnum
  def divisible_by?(dividend)
    self % dividend == 0
  end
end

class Year
  def self.leap?(year)
    return true  if year.divisible_by?(400)
    return false if year.divisible_by?(100)

    year.divisible_by?(4)
  end
end
