require 'date'
class Year
  attr_accessor :year

  def initialize(year)
	@year = year
  end

  def leap?
	divisible_by_four? && (!divisible_by_100? || divisible_by_400?)
  end

  def divisible_by_four?
	year % 4 == 0
  end

  def divisible_by_100?
	year % 100 == 0
  end

  def divisible_by_400?
	year % 400 == 0
  end



end
