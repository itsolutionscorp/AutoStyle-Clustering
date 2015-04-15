class Year
  def initialize year
    @year = year
  end

  def self.leap? year
    new(year).leap?
  end

  def leap?
    if divisible_by? 100
      divisible_by? 400
    else
      divisible_by? 4
    end
  end

  private

  def divisible_by? divisor
    @year % divisor == 0
  end
end
