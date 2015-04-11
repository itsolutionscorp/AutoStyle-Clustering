class Year

  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    self.new(year).leap?
  end

  def leap?
    divisable_by(4) && (!divisable_by(100) || divisable_by(400))
  end

  def divisable_by(number)
    @year % number == 0
  end

end
