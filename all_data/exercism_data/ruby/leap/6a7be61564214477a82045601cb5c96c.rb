class Year

  def initialize(year)
  	@year = year
  end

  def leap?
  	divisible_by?(4) && ( !divisible_by?(100) || divisible_by?(400) )
  end

  private

    def divisible_by?(x)
      @year % x == 0
    end
end
