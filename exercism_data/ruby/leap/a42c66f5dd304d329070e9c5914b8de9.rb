class Year
  def self.leap? year
    (divisible_by?(4, year)) && !(divisible_by?(100, year) && !divisible_by?(400, year))
  end

  def self.divisible_by? num, year
    year % num == 0
  end
end
