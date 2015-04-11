class Year
  def self.leap?(year)
    divisible_by_n?(year, 4) &&
    !divisible_by_n?(year, 100) ||
    divisible_by_n?(year, 400)
  end

  def self.divisible_by_n?(number, n)
    number % n == 0
  end
end
