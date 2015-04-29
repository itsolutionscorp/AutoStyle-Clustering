module Year

  def self.leap?(year)
    self.four_century_check?(year) || (self.one_centure_check?(year) && self.four_year_check?(year))
  end

  def self.four_century_check?(year)
    year % 400 == 0
  end

  def self.one_centure_check?(year)
    year % 100 != 0
  end

  def self.four_year_check?(year)
    year % 4 == 0
  end

end
