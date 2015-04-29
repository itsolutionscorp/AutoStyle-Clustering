class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(value)
    @value = value
  end

  def leap?
    divisible_by?(4) && (!divisible_by?(100) || divisible_by?(400))
  end

  private

  attr_reader :value

  def divisible_by?(number)
    value % number == 0
  end
end
