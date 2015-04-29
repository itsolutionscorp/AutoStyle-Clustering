class Year < Struct.new(:year)
  def self.leap?(year)
    new(year).leap? 
  end

  def leap?
    quadrennial? && !centennial? || quadricentennial?
  end

  def quadrennial?
    year % 4 == 0
  end

  def centennial?
    year % 100 == 0
  end

  def quadricentennial?
    year % 400 == 0
  end
end
