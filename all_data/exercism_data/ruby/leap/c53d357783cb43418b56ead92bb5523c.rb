class Year
  def initialize(year)
    @year = year
  end

#on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    except every year that is evenly divisible by 400.
  def leap?
    @year % 4 == 0 unless leap_exception?
  end

  private
  def exceptional_century?
    @year % 400 == 0
  end

  def century?
    @year % 100 == 0
  end

  def leap_exception?
    century? && !exceptional_century?
  end
end
