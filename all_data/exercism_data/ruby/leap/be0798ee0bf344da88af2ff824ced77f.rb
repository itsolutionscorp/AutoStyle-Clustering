class Year
  def self.leap?(year)
    divisible_by(year, 4) && !divisible_by(year, 100) || divisible_by(year, 400)
  end

  private
  def self.divisible_by(number, divider)
    number.modulo(divider).zero?
  end
end
