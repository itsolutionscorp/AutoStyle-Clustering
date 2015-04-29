class Year

  def self.leap?(year)
    if is_century(year)
      run_century_check(year)
    else
      run_regular_leap_year_check(year)
    end
  end

  private

  def self.is_century(year)
    year % 100 == 0
  end

  def self.run_century_check(year)
    year % 400 == 0
  end

  def self.run_regular_leap_year_check(year)
    year % 4 == 0
  end

end
