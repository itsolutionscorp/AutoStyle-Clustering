class Year
  @year = 0

  def initialize(year)
    @year = year
  end
  
  def leap?
    mod_four = @year % 4
    mod_hundred = @year % 100
    mod_four_hundred = @year % 400
    if (mod_four == 0 and (mod_hundred != 0 or (mod_hundred == 0 and mod_four_hundred == 0))) then
      return true
    else
      return false
    end
  end
end
