class Year
  
  def initialize(year)
    @year = year
  end
  
  def leap?
    self.divides_by?(4) && (!self.divides_by?(100) || self.divides_by?(400))
  end
  
  def inspect
    @year
  end
  
  def to_s
    @year.to_s
  end
  
  protected
  def divides_by?(denominator)
    @year.remainder(denominator).zero?
  end
  
end
