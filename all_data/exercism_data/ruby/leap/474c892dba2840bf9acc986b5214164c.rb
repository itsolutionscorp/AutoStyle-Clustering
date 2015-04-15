module Year
  extend self

  def leap?(year)
    return false if invalid?(year)
    year % 4 == 0
  end

  private

  def invalid?(year)
    year % 100 == 0 && year % 400 != 0
  end
end
