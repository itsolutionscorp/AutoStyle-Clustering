class Year < Struct.new(:year)
  def leap?
    return true if divisible_by?(400)
    return false if divisible_by?(100)

    divisible_by?(4)
  end

  private

  def divisible_by?(number)
    year % number == 0
  end
end
