class Year
  
  attr_reader :number
  def initialize(number)
    @number = number
  end
  
  def leap?
    (divisible_by_4? && !century?) || special_century?
  end

  def divisible_by_4?
    number%4 == 0
  end

  def century?
    number%100 == 0
  end

  def special_century?
    number%400 == 0
  end
end
