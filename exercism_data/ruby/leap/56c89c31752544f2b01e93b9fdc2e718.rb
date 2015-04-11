module Year
  extend self

  def leap?(year)
    return false if !by_4?(year)

    return false if by_100?(year) && !by_400?(year)

    true
  end

  private

  def by_4?(year)
    year % 4 == 0
  end

  def by_100?(year)
    year % 100 == 0
  end

  def by_400?(year)
    year % 400 == 0
  end
end
