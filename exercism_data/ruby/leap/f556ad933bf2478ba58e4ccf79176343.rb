class Year
  class << self
    def leap? year
      centennial, quadricentennial = year % 100 == 0, year % 400 == 0
      exception_to_leap_year = centennial && !quadricentennial

      year % 4 == 0 && !exception_to_leap_year
    end
  end
end
