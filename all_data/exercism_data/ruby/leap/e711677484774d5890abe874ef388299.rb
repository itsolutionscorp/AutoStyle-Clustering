class Year
  attr_accessor :year

  def self.leap?(year)
    self.new(year).leap?
  end

  def initialize(year)
    self.year = year
  end

  def leap?
    if multiple_of_4? && multiple_of_400?
      true
    elsif multiple_of_4? && multiple_of_100?
      false
    elsif multiple_of_4?
      true
    else
      false
    end
  end

  def multiple_of_4?
    multiple_of?(4)
  end

  def multiple_of_100?
    multiple_of?(100)
  end

  def multiple_of_400?
    multiple_of?(400)
  end

  def multiple_of?(x)
    year.modulo(x) == 0
  end
end
