class Year

  attr_reader :annum

  def initialize(annum)
    @annum = annum
  end

  def leap?
    exceptional_century? || vanilla_leap_year? && !century?
  end

  private

  def vanilla_leap_year?
    annum % 4 == 0
  end

  def century?
    annum % 100 == 0
  end

  def exceptional_century?
    annum % 400 == 0
  end

end


class Fixnum

  def leap_year?
    y = Year.new(self)
    y.leap?
  end

end
