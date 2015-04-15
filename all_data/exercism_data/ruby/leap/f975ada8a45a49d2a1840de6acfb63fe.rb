class Year

  attr :year

  def initialize year
    @year = year
  end

  def leap?
    return false unless leap_candidate_year?

    if turn_of_century_year?
      exceptional_century?
    else
      true
    end
  end

  private

  def leap_candidate_year?
    (year % 4).zero?
  end

  def turn_of_century_year?
    (year % 100).zero?
  end

  def exceptional_century?
    (year % 400).zero?
  end

end
