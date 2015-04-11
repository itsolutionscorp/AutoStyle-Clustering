module Year
  extend self

  def leap?(year)
    if div_by(year, 400)
      div_by(year, 4)
    else
      div_by(year, 4) && !div_by(year, 100)
    end
  end

  def div_by(year, n)
    (year % n).zero?
  end
end
