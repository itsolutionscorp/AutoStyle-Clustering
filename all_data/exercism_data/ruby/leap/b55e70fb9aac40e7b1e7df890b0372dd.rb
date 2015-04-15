class Year
  def self.leap? year
    (self.divisible_by_four_hundred? year) ||
      ((self.divisible_by_four? year) &&
        (!self.divisible_by_one_hundred? year))
  end

  def self.divisible_by_four? year
    year % 4 == 0
  end

  def self.divisible_by_one_hundred? year
    year % 100 == 0
  end

  def self.divisible_by_four_hundred? year
    year % 400 == 0
  end
end
