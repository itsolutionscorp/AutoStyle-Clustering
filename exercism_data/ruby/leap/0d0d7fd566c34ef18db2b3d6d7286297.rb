class Year
  def initialize(year)
    @year = year
  end

  def leap?
    vanilla_leap? && century?
  end

  private

  def vanilla_leap?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0 ? exceptional_century? : true
  end

  def exceptional_century?
    @year % 400 == 0 ? true : false
  end
end
