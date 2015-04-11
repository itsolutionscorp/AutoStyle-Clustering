class Year

  attr_reader :value

  def initialize year
    @value = year
  end

  def leap?
    divisible_by?(400) || ( divisible_by?(4) && !divisible_by?(100))
  end

  def divisible_by? number
    value % number == 0
  end
end
