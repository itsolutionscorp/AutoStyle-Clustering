class Year
  attr_accessor :year

  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    self.new(year).leap?
  end

  def leap?
    self.divisible_by_4? and self.valid_century?
  end

  def divisible_by_4?
    @year.modulo(4).zero?
  end

  def valid_century?
    @year.modulo(100) > 0 ||
      (@year.modulo(100).zero? && @year.modulo(400).zero?)
  end
end
