class Year
  class << self
    def leap?(year)
      (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    end

    alias :gregorian_leap? :leap?
    alias :julian_leap? :leap?
  end
end
