class Year
  def initialize year 
    @year = year
  end

  def leap?
    return true   if (@year % 400).eql? 0
    return false  if (@year % 100).eql? 0
    return true   if (@year % 4  ).eql? 0
  end
end
