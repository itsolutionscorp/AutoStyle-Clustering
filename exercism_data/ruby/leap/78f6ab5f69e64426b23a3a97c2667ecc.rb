class Year

  class LeapYear
    def initialize(year)
      @year = year
    end

    def divisible_by?(arg)
      @year.modulo(arg) == 0
    end

  end

  def initialize(year)
    @year = LeapYear.new(year)
  end

  def leap?
    ((@year.divisible_by?(4) && !@year.divisible_by?(100)) || @year.divisible_by?(400))
  end

end
