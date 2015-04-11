class Year
  def self.leap?(year)
    Year.new(year).fourth_year_et_al?
  end

  def initialize(year)
    @year = year
  end

  def fourth_year_et_al?
    @year%4 == 0 && !valid_century?
  end

  def valid_century?
    @year%100 == 0 && !fourth_century?
  end

  def fourth_century?
    @year%400 == 0
  end
end
