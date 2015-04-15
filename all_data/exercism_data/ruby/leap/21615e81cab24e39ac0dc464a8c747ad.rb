# encoding: utf-8

class Year
  def initialize year
    @year = year
  end

  def leap?
    div_by_4? && (!century? || quad_century?)
  end

  protected
  def div_by_4?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def quad_century?
    @year % 400 == 0
  end
end
