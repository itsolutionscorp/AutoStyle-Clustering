class Year
  def self.leap?(year)
    divisible_by(4, year) && !divisible_by(100, year) || divisible_by(400, year)
  end

  def self.divisible_by(n, year)
    year % n == 0
  end

  private_class_method :divisible_by
end
