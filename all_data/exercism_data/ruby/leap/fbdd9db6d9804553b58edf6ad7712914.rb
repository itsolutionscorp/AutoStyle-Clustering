class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    @leap ||= 
      divisible_by_4? and (!century? or quatercentury?)
  end

  private
  
  def divisible_by_4?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def quatercentury?
    year % 400 == 0
  end

end
