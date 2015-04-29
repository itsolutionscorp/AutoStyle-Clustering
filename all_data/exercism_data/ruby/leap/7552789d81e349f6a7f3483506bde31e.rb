class Numeric
  def divisible_by?(divisor)
    self % divisor == 0
  end
end

module Year
  extend self

  def leap?(year)
    year.divisible_by?(4) && (!year.divisible_by?(100) || year.divisible_by?(400))
  end

end
