module Year
  extend self

  def leap?(year)
    return fourth_year?(year) if div_by?(year, 400)
    return fourth_year_of_a_non_century?(year)
  end

  private
  def fourth_year_of_a_non_century?(year)
    fourth_year?(year) && !century?(year)
  end

  def century?(year)
    div_by?(year, 100)
  end

  def fourth_year?(year)
    div_by?(year, 4)
  end

  def div_by?(year, n)
    (year % n).zero?
  end
end
