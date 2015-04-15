class Year
  def initialize value
    @value = value
  end

  def leap?
    if divisible_by? 4
      return true if fourth_century? 
      century? ? false : true 
    else
      false
    end
  end

  def century?
    divisible_by? 100
  end

  def fourth_century?
    divisible_by? 400
  end

  private

  def divisible_by? number
    @value % number == 0
  end
  
end
