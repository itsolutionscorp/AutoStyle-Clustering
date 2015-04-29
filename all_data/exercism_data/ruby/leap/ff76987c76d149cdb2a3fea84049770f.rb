class Year

  def self.leap?(year)
    Year.new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    century_is_leap? or year_is_leap? 
  end

  def century_is_leap?
    century? && divisible_by(400)
  end

  def year_is_leap?
    !century? && divisible_by(4)
  end

  def century?
    divisible_by(100)
  end

  private

  def divisible_by(divisor)
    _, remainder = @year.divmod(divisor)
    remainder == 0
  end

end
