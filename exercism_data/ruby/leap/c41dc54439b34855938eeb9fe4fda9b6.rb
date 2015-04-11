class Year
  def initialize value
    @value = value
  end

  def leap?
      return true if fourth_century? 
      return false if century?
      (divisible_by? 4) ? true : false
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
