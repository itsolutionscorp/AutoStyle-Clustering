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
    normal_common_year || exceptional_common_year
  end

  def normal_leap_year
    divisible_by(4)
  end

  def normal_common_year
    !normal_leap_year
  end

  def exceptional_common_year
    divisible_by(100) && !divisible_by(400)
  end

  def divisible_by(number)
    year % number == 0
  end
end
