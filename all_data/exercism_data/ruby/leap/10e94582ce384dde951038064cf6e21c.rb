module Year
  extend self

  def leap?(year)
    return div_by_4?(year) if  div_by?(year, 400)
    return div_by_4?(year) && !div_by?(year, 100)
  end

  private
  def div_by_4?(year)
    div_by?(year, 4)
  end

  def div_by?(year, n)
    (year % n).zero?
  end
end
