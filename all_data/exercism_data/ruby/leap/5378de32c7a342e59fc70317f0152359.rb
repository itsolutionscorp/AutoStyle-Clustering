class Integer
  def evenly_divisible_by? other
    (self % other).zero?
  end
end
module Year
  def leap? year
    year.evenly_divisible_by?(400) ||
    year.evenly_divisible_by?(4) && !year.evenly_divisible_by?(100)
  end
end
Year.extend(Year)
