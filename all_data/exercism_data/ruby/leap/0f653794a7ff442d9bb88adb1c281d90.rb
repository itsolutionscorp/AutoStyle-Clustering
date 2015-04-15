class Year
  attr_reader :year
  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    new(year).leap?
  end

  def leap?
    !common?
  end

  private

  def common?
    !divisible_by(4) || exceptional_common_year
  end

  def exceptional_common_year
    divisible_by(100) && !divisible_by(400)
  end

  def divisible_by(number)
    year % number == 0
  end
end
