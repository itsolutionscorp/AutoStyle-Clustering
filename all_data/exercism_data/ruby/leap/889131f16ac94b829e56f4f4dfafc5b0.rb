class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    return true  if divisable_by? 400
    return false if divisable_by? 100
    divisable_by? 4
  end

  private

  def divisable_by?(num)
    @year % num == 0
  end
end
