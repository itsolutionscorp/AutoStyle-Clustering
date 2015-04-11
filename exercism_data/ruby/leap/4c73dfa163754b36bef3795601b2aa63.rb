class Year
  def initialize(arg)  
    @year = arg       
  end
  def leap?
    if @year % 400 == 0
      true
    elsif @year % 100 == 0 
      false
    elsif @year % 4 == 0
      true
    else false
    end
  end
end
