class Year
  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    satisfies_rule_for_4?
  end

  private

  def satisfies_rule_for_4?
    divisible_by?(4) && !satisfies_rule_for_100?
  end

  def satisfies_rule_for_100?
    divisible_by?(100) && !satisfies_rule_for_400?
  end

  def satisfies_rule_for_400?
    divisible_by?(400)
  end

  def divisible_by? number
    year % number == 0
  end
end
