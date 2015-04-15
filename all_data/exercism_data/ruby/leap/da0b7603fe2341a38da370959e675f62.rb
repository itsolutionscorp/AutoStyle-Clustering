class Year

  def self.leap?(year)
    (divisible_by?(400, year) || ((divisible_by?(4, year) && (! divisible_by?(100, year)))))? true : false
  end

  def self.divisible_by?(divisible, year)
    year % divisible == 0
  end

end
