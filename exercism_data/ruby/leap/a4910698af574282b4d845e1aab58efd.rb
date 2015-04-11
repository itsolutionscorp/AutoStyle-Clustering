class Year < Struct.new(:year)
  def leap?
    number? && (!century? || exceptional_century?)
  end

  def number?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def exceptional_century?
    year % 400 == 0
  end
end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
