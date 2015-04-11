class Year

  def initialize(year)
  	@year = year
  end

  def leap?
  	if divisible_by? 400
  	  true
  	elsif divisible_by? 100
  	  false
  	elsif divisible_by? 4
  	  true
  	else
  	  false
  	end
  end

  private

    def divisible_by?(x)
      @year % x == 0
    end
end
