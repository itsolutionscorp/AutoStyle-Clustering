module Year
  extend self

  def leap?(year)
    return true  if year % 400 == 0
    return false if year % 100 == 0
    year % 4 == 0
  end

end
