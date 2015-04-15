class Year
  attr_reader :number
  def initialize(number)
    @number = number
  end
  
  def leap?
    return special_century? || (!century? && divisible_by_4?)
  end

  def divisible_by_4?
    return number%4 == 0
  end

  def century?
    return number%100 == 0
  end

  def special_century?
    return number%400 == 0
  end
end
