module Year

  extend self

  def leap?(year)
    (year % 400).zero? ||
    (year % 4).zero? && !(year % 100).zero?
  end

end
