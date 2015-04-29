class Year
  def self.leap?(year)
    if divisible_by?(400, year)
        true
    elsif (divisible_by?(4, year) && (! divisible_by?(100, year)))
        true
    else
        false
    end
  end
  def self.divisible_by?(divisible, year)
    year % divisible == 0
  end
end
