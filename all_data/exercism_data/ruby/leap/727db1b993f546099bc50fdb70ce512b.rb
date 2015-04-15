class Year
  def self.leap?(year)
    divisible_by_4?(year) && (!divisible_by_100?(year) || divisible_by_400?(year))
  end

  def self.divisible_by_4?(year)
    year % 4 == 0
  end

  def self.divisible_by_100?(year)
    year % 100 == 0
  end

  def self.divisible_by_400?(year)
    year % 400 == 0
  end

  private_class_method :divisible_by_4?, :divisible_by_100?, :divisible_by_400?
end
