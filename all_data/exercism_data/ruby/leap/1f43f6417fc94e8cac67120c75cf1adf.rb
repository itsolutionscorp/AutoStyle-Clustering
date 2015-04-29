# encoding: utf-8

class Year
  def initialize(year)
    @year = year
  end

  def century?
    (@year % 100 == 0)
  end

  def century_exception?
    (@year % 400) == 0
  end

  def vanilla?
    (@year % 4) == 0
  end

  def leap?
    (vanilla? && !century?) || century_exception?
  end
end
