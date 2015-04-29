# Utility class to check whether given year is a leap year
class Year
  def self.leap?(year)
    raise ArgumentError unless year.is_a? Integer

    leap_year?(year) && (quad_century?(year) || !century?(year))
  end

  def self.leap_year?(year)
    divisible?(year, 4)
  end

  def self.century?(year)
    divisible?(year, 100)
  end

  def self.quad_century?(year)
    divisible?(year, 400)
  end

  def self.divisible?(year, divisor)
    year % divisor == 0
  end

  private_class_method :leap_year?, :century?, :quad_century?, :divisible?
end
