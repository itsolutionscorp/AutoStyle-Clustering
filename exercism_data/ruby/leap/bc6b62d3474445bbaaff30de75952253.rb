class Year
  def initialize(year)
    @year = year
  end

  def leap?
    [ ExceptionalLeapYear,
      Century,
      VanillaLeapYear,
      NotLeapYear
    ].find{ |special_year| special_year.new.match?(@year) }.new.leap?
  end
end

class ExceptionalLeapYear
  def match?(year)
    year % 400 == 0
  end

  def leap?
    true
  end
end

class Century
  def match?(year)
    year % 100 == 0
  end

  def leap?
    false
  end
end

class VanillaLeapYear
  def match?(year)
    year % 4 == 0
  end

  def leap?
    true
  end
end

class NotLeapYear
  def match?(year)
    year % 4 != 0
  end

  def leap?
    false
  end
end
