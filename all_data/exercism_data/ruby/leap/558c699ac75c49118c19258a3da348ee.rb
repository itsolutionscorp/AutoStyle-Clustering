class Year
  attr_accessor :new
  
  def initialize(new = 0)
    @new = new
  end
  
  def leap?
    if @new % 400 == 0
      true
    elsif @new % 100 == 0
      false
    elsif @new % 4 == 0
      true
    else 
      false
    end
  end
end
