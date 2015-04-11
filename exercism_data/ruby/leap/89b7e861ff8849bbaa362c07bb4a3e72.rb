class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if year % 400 == 0 then return true
    elsif year % 100 == 0 then return false
    elsif year % 4 == 0 then return true
    else return false
    end
  end

  private
  attr_reader :year
end
