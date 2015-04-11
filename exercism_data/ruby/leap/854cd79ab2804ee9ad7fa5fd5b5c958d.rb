class Year < Struct.new(:number)
  def leap?
    quadrennial? && (!centennial? || quadricentennial?)
  end

  def quadrennial?
    number % 4 == 0
  end

  def centennial?
    number % 100 == 0
  end

  def quadricentennial?
    number % 400 == 0
  end
end

class Fixnum
  def leap_year?
    Year.new(self).leap?
  end
end
