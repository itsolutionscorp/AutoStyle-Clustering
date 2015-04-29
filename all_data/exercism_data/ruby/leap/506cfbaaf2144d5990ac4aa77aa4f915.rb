class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    if !divisible_by? 4
      false
    elsif !divisible_by? 100
      true
    elsif divisible_by? 400
      true
    else
      false
    end
  end

  private

  def divisible_by?(divisor)
    @year % divisor == 0
  end
end
