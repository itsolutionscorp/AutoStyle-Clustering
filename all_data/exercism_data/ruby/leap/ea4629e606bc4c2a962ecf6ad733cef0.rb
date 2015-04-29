class Year
  def initialize year
    @year = year
  end

  def leap?
    return true if divisible_by 400
    return false if divisible_by 100
    return true if divisible_by 4
  end

  def divisible_by amount
    @year.modulo(amount).zero?
  end
end
