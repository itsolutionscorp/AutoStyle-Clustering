# Reports if a year is leap.
class Year
  def self.leap?(year)
    multiple_of_400?(year) || !multiple_of_100?(year) && multiple_of_4?(year)
  end

  def self.multiple_of_400?(year)
    year % 400 == 0
  end

  def self.multiple_of_100?(year)
    year % 100 == 0
  end

  def self.multiple_of_4?(year)
    year % 4 == 0
  end
end
