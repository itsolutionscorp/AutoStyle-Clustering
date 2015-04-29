class Fixnum
  def divisible_by? number
    self % number == 0
  end
end

class Year

  def initialize year
    @year = year
  end

  def leap?
    (@year.divisible_by? 400) || (@year.divisible_by? 4 unless @year.divisible_by? 100)
  end

end
