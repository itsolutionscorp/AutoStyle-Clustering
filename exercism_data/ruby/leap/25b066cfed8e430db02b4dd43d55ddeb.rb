class Year < Struct.new(:year)
  def leap?
    year.leap_year?
  end
end

class Fixnum
  def leap_year?
    leap_number? && (!century? || leap_century?)
  end

private

  def leap_number?
    self % 4 == 0
  end

  def century?
    self % 100 == 0
  end

  def leap_century?
    self % 400 == 0
  end
end
