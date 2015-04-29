class Year
  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    return mod_4? & !mod_100? unless mod_400?
    return mod_4?             unless mod_100?
    mod_4?
  end

  private

  def mod_4?
    year % 4 == 0
  end

  def mod_100?
    year % 100 == 0
  end

  def mod_400?
    year % 400 == 0
  end
end
