class Year
  def initialize year
    @year = year
  end
  def leap?
    status = @year % 4 == 0 && @year % 400 == 0
    return status
  end
end

a = Year.new(1900)
p a.leap?
