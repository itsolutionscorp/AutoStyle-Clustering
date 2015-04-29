class Year
  def initialize(year)
    @year = year
  end

  def leap?
    # I don't cheat with Date.leap? here ;)
    evenly_divisible?(4) && !evenly_divisible?(100) || evenly_divisible?(400)
  end

  private

  def evenly_divisible?(by)
    @year % by == 0
  end
end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
