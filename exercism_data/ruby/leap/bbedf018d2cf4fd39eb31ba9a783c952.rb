class Year
  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    new(year).leap?
  end

  def leap?
    quadrennial? && (quadricentennial? || !centennial?)
  end

  private

  def quadrennial?
    multiple_of?(4)
  end

  def quadricentennial?
    multiple_of?(400)
  end

  def centennial?
    multiple_of?(100)
  end

  def multiple_of?(number)
    (@year % number).zero?
  end
end
