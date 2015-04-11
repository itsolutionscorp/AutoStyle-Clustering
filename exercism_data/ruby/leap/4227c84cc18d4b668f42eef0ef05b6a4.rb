class Year < Date
end

class Date
  def leap?(year)
    true if year % 4 == 0
  end

  alias :gregorian_leap? :leap?
  alias :julian_leap? :leap?
end
