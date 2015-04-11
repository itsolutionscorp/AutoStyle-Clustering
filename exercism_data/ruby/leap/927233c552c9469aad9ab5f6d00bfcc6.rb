module Year

  def self.leap?(year)
    divisible_by_4(year) &&
    (not_a_century(year) || four_hundred_century(year))
  end

  private

  def self.divisible_by_4(year)
    year % 4 == 0
  end

  def self.not_a_century(year)
    year % 100 != 0
  end

  def self.four_hundred_century(year)
    year % 400 == 0
  end
end
