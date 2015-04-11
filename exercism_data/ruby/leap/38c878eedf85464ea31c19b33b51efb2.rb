class Year
  attr_accessor :year

  class << self
    def leap? year
      Year.new(year).leap?
    end
  end

  def initialize(year)
    self.year = year
  end

  def leap?
    return true  if year%400 == 0
    return false if year%100 == 0
    year%4 == 0
  end
end
