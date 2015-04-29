require 'date'

class Year
  def initialize(year)
    @year = year
  end

  def year
    @year
  end

  def self.leap? year
    return true if fourth_century?(year)
    return false if century?(year)
    return true if fourth_year?(year)
    return false
  end

  private
  def self.fourth_century?(year)
    return true if year % 400 == 0
  end
  def self.century?(year)
    return true if year % 100 == 0
  end
  def self.fourth_year?(year)
    return true if year % 4 == 0
  end
end
