class Year
  def self.leap? year
    new(year).leap?
  end
  
  def initialize year
    self.year = year
  end
  
  def leap?
    normal_leap? || century_leap?
  end
  
  private
  attr_accessor :year
  
  def normal_leap?
    year.divisible_by?(4) && !year.divisible_by?(100)
  end
  
  def century_leap?
    year.divisible_by?(400)
  end
end

class Fixnum
  def divisible_by? other
    modulo(other).zero?
  end
end
