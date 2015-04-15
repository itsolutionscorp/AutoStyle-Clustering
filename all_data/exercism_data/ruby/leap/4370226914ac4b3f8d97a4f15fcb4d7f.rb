class Year
  def initialize year
    @year = year
  end

  def leap?
    case
    when every_fourth_century? then true
    when century? then false
    when every_fourth_year? then true
    else
      false
    end
  end

  private

  def every_fourth_century?
    @year % 400 == 0
  end

  def century?
    @year % 100 == 0
  end

  def every_fourth_year?
    @year % 4 == 0
  end
end
