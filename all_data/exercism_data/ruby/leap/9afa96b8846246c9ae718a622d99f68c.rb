class Year
  def self.leap? year
    leap = divisible?(year, 4) unless divisible?(year, 100)
    leap.nil? ? divisible?(year, 400) : leap
  end

  private

  def self.divisible? year, mod
    year % mod == 0
  end
end
