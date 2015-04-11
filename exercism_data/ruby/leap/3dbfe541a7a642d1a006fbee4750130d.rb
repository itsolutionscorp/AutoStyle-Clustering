class Year
  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    mod?(4) & !mod?(100) || mod?(400)
  end

  private

  def mod? number
    year % number == 0
  end
end
