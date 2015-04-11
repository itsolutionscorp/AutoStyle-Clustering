module Year
  extend self

  def leap?(year)
    divisible_by_four?(year) && (!round_century?(year) || divisible_by_400?(year))
  end

private

  def divisible_by_four?(year)
    year % 4 == 0
  end

  def round_century?(year)
    year % 100 == 0
  end

  def divisible_by_400?(year)
    year % 400 == 0
  end
end
