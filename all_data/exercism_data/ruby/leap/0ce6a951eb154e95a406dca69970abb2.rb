module Year

  extend self

  def leap?(year)
    multiple_of?(400, year) ||
    multiple_of?(4, year) && !multiple_of?(100, year)
  end

  def multiple_of?(divisor, year)
    (year % divisor).zero?
  end

end
