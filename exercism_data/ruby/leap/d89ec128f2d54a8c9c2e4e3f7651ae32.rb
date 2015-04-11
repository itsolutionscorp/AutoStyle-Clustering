class Year

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def leap?
    (regular? && !century?) || exceptional?
  end

  private

  def regular?
    number % 4 == 0
  end

  def century?
    number % 100 == 0
  end

  def exceptional?
    number % 400 == 0
  end

end
