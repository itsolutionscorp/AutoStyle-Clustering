module Year

  def self.leap?(year)
    self.year_check(year)
  end

  def self.year_check(year)
    self.four_century_check?(year)
  end

  def self.four_century_check?(year)
    (year % 400 == 0) ? true : self.one_centure_check?(year)
  end

  def self.one_centure_check?(year)
    (year % 100 == 0) ? false : self.four_year_check?(year)
  end

  def self.four_year_check?(year)
    (year % 4 == 0) ? true : false
  end

end
