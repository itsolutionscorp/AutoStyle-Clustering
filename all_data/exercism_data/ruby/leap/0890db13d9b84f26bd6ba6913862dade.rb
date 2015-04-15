class Year
  def initialize(year)
    @year = year
  end

  def leap?
    on every year that is evenly divisible by 4
      except every year that is evenly divisible by 100
        except every year that is evenly divisible by 400
  end

  def on(leap)
    @positive = true
    @leap = leap
  end

  def except(leap)
    @positive = !@positive
    @leap = leap ? @positive : @leap
  end

  def by(n)
    @year % n == 0
  end

  def method_missing(meth, leap)
    leap
  end
end
