class Year
  def self.leap?(year)
    fourth_century?(year) || fourth_year?(year) && century?(year)
  end

private

  def self.fourth_year?(year)
    year_divisible_by?(year, 4)
  end

  def self.century?(year)
    !year_divisible_by?(year, 100)
  end

  def self.fourth_century?(year)
    year_divisible_by?(year, 400)
  end

  def self.year_divisible_by?(year, demoninator)
    year % demoninator == 0
  end
end
