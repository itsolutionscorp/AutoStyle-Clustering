class Year
  def self.divisible_by_400?(yr)
    (yr % 400).zero?
  end
  def self.divisible_by_100?(yr)
    (yr % 100).zero?
  end
  def self.divisible_by_4?(yr)
    (yr % 4).zero?
  end
  def self.leap?(year)
    divisible_by_4?(year) && !divisible_by_100?(year) || divisible_by_400?(year)
  end
end
