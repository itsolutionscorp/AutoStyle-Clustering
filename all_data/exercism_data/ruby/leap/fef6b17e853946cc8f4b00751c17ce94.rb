class Year

  def self.leap? year
    new( year ).leap?
  end

  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    divisible?(4) and not ( divisible?(100) and not divisible?(400) )
  end

private

  def divisible? factor
    year % factor == 0
  end

end
