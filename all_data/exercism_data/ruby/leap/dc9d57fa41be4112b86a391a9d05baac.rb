class Year
  def self.leap?(year)
    year_is_divisible_by_400(year) || year_is_not_divisible_by_100(year) && \
      year_is_divisible_by_4(year)
  end

  def self.year_is_divisible_by_4(year)
    year % 4 == 0
  end

  def self.year_is_not_divisible_by_100(year)
    year % 100 != 0
  end

  def self.year_is_divisible_by_400(year)
    year % 400 == 0
  end
end
