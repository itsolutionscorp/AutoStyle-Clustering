## This code tests years and returns true if the year is a leapyear and
#false if it is not.
#
## Ozzie Gooen, 2014
#
class Year

  def initialize(year)
    @year = year
  end

  def leap?
    div_by(400) or (!div_by(100) and div_by(4))
  end

  def self.leap?(year)
    year = Year.new(year)
    year.leap?
  end

  private 

  def div_by(i)
    @year%i == 0
  end

end
