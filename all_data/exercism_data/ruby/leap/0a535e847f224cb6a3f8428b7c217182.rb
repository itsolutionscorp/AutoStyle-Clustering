class Year
  def initialize(year)
    @year = year.to_i
  end

  def leap?
    if @year%4 == 0
      @year%400 == 0 || @year%100 != 0
    else
      false
    end
  end

end
