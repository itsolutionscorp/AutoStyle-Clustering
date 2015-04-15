class Year < Struct.new(:year)
  def leap?
    divisible_by?(4) unless divisible_by?(100) && !divisible_by?(400)
  end

  private

  def divisible_by?(number)
    year % number == 0
  end
end
