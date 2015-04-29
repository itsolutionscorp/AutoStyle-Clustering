class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by_four?
      if century?
        if exceptional_century?
          true
        else
          false
        end
      else
        true
      end
    else
      false
    end
  end

  def divisible_by_four?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def exceptional_century?
    year % 400 == 0
  end
end
