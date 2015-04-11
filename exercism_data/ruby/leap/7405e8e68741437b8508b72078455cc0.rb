class Year

  def initialize(date)
    @date = date
  end

  def leap?
    vanilla_leap? && century_comparison?
  end

  private
  
  attr_reader :date

  def vanilla_leap?
    date % 4 == 0
  end

  def century_comparison?
    no_century?  || exceptional_century?
  end

  def no_century?
    date % 100 != 0
  end

  def exceptional_century?
    date % 400 == 0
  end
end
