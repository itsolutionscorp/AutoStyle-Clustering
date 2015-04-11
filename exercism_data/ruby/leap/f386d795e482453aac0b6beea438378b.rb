class Year
  def initialize year
    @year = year
  end

  def leap?
    (normal_leap? && not_century?) ||
      special_century?
  end

  private
  attr_reader :year

  def normal_leap?
    multiple_of?(4)
  end

  def not_century?
    !multiple_of?(100)
  end

  def special_century?
    multiple_of?(400)
  end

  def multiple_of? number
    (year % number).zero?
  end

end
