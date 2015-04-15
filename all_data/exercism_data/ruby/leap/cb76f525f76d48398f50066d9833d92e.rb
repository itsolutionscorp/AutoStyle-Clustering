class Year
  def self.leap?(year)
    if divisible_by_4?(year)
      return (divisible_by_100?(year) ? false : true) unless divisible_by_400?(year)
      return true
    else
      return false
    end
  end

private
  def self.divisible_by_4?(year)
    year%4==0 ? true : false
  end
  def self.divisible_by_100?(year)
    year%100==0 ? true : false
  end
  def self.divisible_by_400?(year)
    year%400==0 ? true : false
  end
end
