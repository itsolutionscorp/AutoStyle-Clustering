class Year
  def self.leap?(year)
    non_century_year?(year) || leap_cycle?(year) if possible_leap?(year)
  end

  private
  def self.possible_leap?(year)
    year % 4 == 0
  end

  def self.non_century_year?(year)
    year % 100 != 0
  end

  def self.leap_cycle?(year)
    year % 100 == 0 && year % 400 == 0
  end
end
