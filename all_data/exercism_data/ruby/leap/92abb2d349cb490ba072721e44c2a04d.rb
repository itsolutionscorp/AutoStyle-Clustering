class Integer
  def divisible? other
    self % other == 0
  end
end

class Year
  attr_reader :year

  def initialize year
    self.year = year
  end

  def leap?
    year.divisible?(4) && (! year.divisible?(100) || year.divisible?(400))
  end

  private

  attr_writer :year
end
