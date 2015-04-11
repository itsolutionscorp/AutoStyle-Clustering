class Integer
  def evenly_divisible_by? other
    (self % other).zero?
  end
end
module Year
  def leap? year = 0
    year = self if self.is_a? Fixnum
    year.evenly_divisible_by?(400) ||
      year.evenly_divisible_by?(4) && !year.evenly_divisible_by?(100)
  end
end
Year.extend(Year)

if __FILE__ == $PROGRAM_NAME
  years = [2000, 1999, 1600, 1900]
  years.each do |year|
    Year.leap? year # => true, false, true, false
  end
  Fixnum.include(Year)
  years.each do |year|
    year.leap? # => true, false, true, false
  end
end
