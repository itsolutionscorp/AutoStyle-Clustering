class Year
	def self.leap?(year)
    return true if divisible_by?(400,year)
    return true if divisible_by?(4,year) unless divisible_by?(100,year)
    return false
  end

  def self.divisible_by?(x,year)
    year % x == 0
  end
end
