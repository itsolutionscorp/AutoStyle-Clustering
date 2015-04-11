class Year
  def initialize(num)
    @num = num
  end

  def leap?
    quad_century? || (!century? && quad?)
  end

  private
  attr_reader :num

  def quad_century?
    num % 400 == 0
  end

  def century?
    num % 100 == 0
  end

  def quad?
    num % 4 == 0
  end
end
