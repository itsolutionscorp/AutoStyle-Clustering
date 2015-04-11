class Year

  def initialize value
    @value = value
  end

  def leap?
      divisible_by?(4) && ( divisible_by?(400)  ||  !divisible_by?(100) ) 
  end


  private

  def divisible_by? number
    @value % number == 0
  end
  
end
