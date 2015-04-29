class Year
  def initialize year
    @year = year
  end

  def leap?
    return false if @year%100==0 and not @year%400==0
    return true if @year%4==0
    false
  end
end
