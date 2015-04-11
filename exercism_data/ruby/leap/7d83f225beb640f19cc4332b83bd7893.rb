class Year
  def initialize( year )
    @year = year
  end

  def leap?
    if year_century?
      if year_fourth_century?
        true
      else
        false
      end
    else
      if leap_year?
        true
      else
        false
      end
    end
  end

  private
  def leap_year?
    if @year % 4 == 0
      true
    else
      false
    end
  end

  def year_century?
    if @year % 100 == 0
      true
    else
      false
    end
  end

  def year_fourth_century?
    if @year % 400 == 0
      true
    else
      false
    end
  end
end
