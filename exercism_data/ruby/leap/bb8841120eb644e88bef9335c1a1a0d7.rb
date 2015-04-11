class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by? 400
      true
    elsif divisible_by? 4 and not divisible_by? 100
      true
    else
      false
    end
  end

  private

  def divisible_by?(num)
    @year % num == 0
  end
end
