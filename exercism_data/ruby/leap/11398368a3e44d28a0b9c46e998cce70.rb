module Year
  extend self

  def leap?(year)
    return false if century(year)
    year % 4 == 0
  end

  def century(year)
    year % 100 == 0 && year % 400 != 0
  end
end
