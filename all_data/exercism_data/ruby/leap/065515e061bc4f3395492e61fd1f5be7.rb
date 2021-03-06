class Fixnum
  def divisible_by?(n)
    self % n == 0
  end
end

class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if @year.divisible_by? 400
      true
    elsif @year.divisible_by? 100
      false
    else
      @year.divisible_by? 4
    end
  end
end
