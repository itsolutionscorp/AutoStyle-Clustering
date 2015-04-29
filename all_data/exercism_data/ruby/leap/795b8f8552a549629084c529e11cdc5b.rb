class Year
  def initialize(year)
    @year = year
  end

  def leap?
    @year%4 == 0 ?  hundred_year_rule? : false
  end

  def hundred_year_rule?
    @year%100 == 0 ? @year%400 == 0 : true
  end
end
