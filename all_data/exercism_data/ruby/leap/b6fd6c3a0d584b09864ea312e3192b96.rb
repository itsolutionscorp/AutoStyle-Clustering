class Year

  def self.leap?(year)
    divisible_by_four?(year) && not_divisible_by_one_hundred?(year) || divisible_by_four_hundred?(year)
  end

  private

  def self.divisible_by_four?(year)
    (year % 4).zero?
  end

  def self.not_divisible_by_one_hundred?(year)
    !(year % 100).zero?
  end

  def self.divisible_by_four_hundred?(year)
    (year % 400).zero?
  end

end
