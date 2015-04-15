require 'date'

class Year < Date
  def initialize(year)
    @year = year
  end

  def self.year
    @year
  end

  def leap?
    return true if self.fourth_century?
    return false if self.century?
    return true if self.fourth_year?
    return false
  end

  private
  def century?
    return true if self.year % 100 == 0
  end
  def fourth_century?
    return true if self.year % 400 == 0
  end
  def fourth_year?
    return true if self.year % 4 == 0
  end
end
