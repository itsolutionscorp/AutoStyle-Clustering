class Year
  attr_reader :number

  def initialize number
    @number = number
  end

  def leap?
    (any_old_year? && !century?) || exceptional_century?
  end

  def any_old_year?
    number % 4 == 0
  end

  def century?
    number % 100 == 0
  end

  def exceptional_century?
    number % 400 == 0
  end

end
